package com.dream.city.service.handler.impl;

import com.alibaba.fastjson.JSON;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.*;
import com.dream.city.base.model.enu.*;
import com.dream.city.base.model.req.CityInvestReq;
import com.dream.city.base.model.req.InvestOrderReq;
import com.dream.city.base.model.resp.InvestOrderResp;
import com.dream.city.base.model.resp.InvestResp;
import com.dream.city.base.model.resp.PlayerResp;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.base.utils.KeyGenerator;
import com.dream.city.service.consumer.*;
import com.dream.city.service.handler.CommonService;
import com.dream.city.service.handler.OrderService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Wvv
 * @program: dream-city
 * @File: OrderServiceImpl
 * @description: 订单服务类
 * @create: 2019/10/2019/10/28 00:47:31 [星期一]
 **/
@Service
public class OrderServiceImpl implements OrderService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConsumerOrderService orderService;
    @Autowired
    private ConsumerPropertyService propertyService;
    @Autowired
    ConsumerInvestService investService;
    @Autowired
    private ConsumerAccountService accountService;
    @Autowired
    private ConsumerTradeService tradeService;
    @Autowired
    private ConsumerTradeVerifyService verifyService;
    //@Autowired
    //private ConsumerCommonsService commonsService;
    @Autowired
    private ConsumerPlayerService playerService;
    @Autowired
    private ConsumerEarningService earningService;

    @Autowired
    CommonService commonService;


    @LcnTransaction
    @Transactional
    @Override
    public Message  playerInvest(Message msg) throws BusinessException {
        logger.info("预约投资:{}", msg);
        String desc = "";
        boolean success = Boolean.FALSE;
        Map<String, Object> result = new HashMap<>();
        try {
            InvestOrderReq orderReq = DataUtils.getInvestOrderReqFromMessage(msg);
            Player player = null;
            if (StringUtils.isNotBlank(orderReq.getPlayerId())) {
                player = playerService.getPlayerByPlayerId(orderReq.getPlayerId());
            }
            if (player == null && StringUtils.isNotBlank(orderReq.getPayerName())) {
                PlayerResp playerResp = commonService.getPlayerByStrUserName(orderReq.getPayerName());
                if (playerResp != null) {
                    player = DataUtils.toJavaObject(playerResp, Player.class);
                }
            }
            if (player == null) {
                msg.setCode(ReturnStatus.ERROR.getStatus());
                msg.setDesc("找不到玩家账号");
                return msg;
            }
            //获取项目数据
            InvestResp invest = getInvestByIdOrinName(orderReq.getInvestId(), orderReq.getInName(), orderReq.getInType());
            orderReq.setOrderAmount(invest.getInLimit());
            orderReq.setPersonalInTax(invest.getPersonalInTax());
            //获取当前时间  后改为数据库时间 TODO
            Date investTime = new Date();

            //校验项目投资规则
            //1是否复投
            int orderRepeat = 0;
            InvestOrderReq getOrdersReq = new InvestOrderReq();
            getOrdersReq.setInvestId(orderReq.getInvestId());
            getOrdersReq.setPlayerId(player.getPlayerId());
            Result<List<InvestOrderResp>> getOrdersResult = orderService.getOrders(getOrdersReq);
            List<InvestOrderResp> orderList = getOrdersResult.getData();
            if (orderList != null && orderList.size() > 0) {
                orderRepeat = 1;
            }
            //2项目开始投资时间
            if (investTime.before(invest.getInStart())) {
                desc = "项目还未开始投资";
            }
            //3项目结束投资时间
            if (investTime.after(invest.getInEnd())) {
                desc = "项目投资已结束";
            }
            //4限额
        /*if (orderReq.getOrderAmount().compareTo(invest.getInLimit())>0){
            desc = "超过项目投资限额，投资限额为：" + invest.getInLimit();
        }*/
            Result<PlayerAccount> playerAccountResult = accountService.getPlayerAccount(player.getPlayerId());
            PlayerAccount playerAccount = DataUtils.toJavaObject(playerAccountResult.getData(), PlayerAccount.class);
            //5USDT不足
            if (invest.getInLimit().compareTo(playerAccount.getAccUsdtAvailable()) > 0) {
                desc = "USDT不足";
            }
            //6MT不足
            if (BigDecimal.valueOf(Double.parseDouble(String.valueOf(invest.getPersonalInTax()))).compareTo(playerAccount.getAccMtAvailable()) > 0) {
                desc = "MT不足";
            }

            //返回数据
            result.put("investId", "");
            result.put("usdtFreeze", BigDecimal.ZERO);
            result.put("mtFreeze", BigDecimal.ZERO);
            result.put("state", ReturnStatus.INVEST_SUBSCRIBE.getStatus());

            //生成订单
            Result<InvestOrder> orderResult = this.orderCreate(invest, player.getPlayerId(), orderRepeat, desc);
            InvestOrder order = null;
            if (orderResult != null && orderResult.getData() != null) {
                order = orderResult.getData();
                result.put("investId", order.getOrderInvestId());

                PlayerEarning insertEarningReq = new PlayerEarning();
                insertEarningReq.setIsWithdrew(0);
                insertEarningReq.setEarnMax(invest.getInLimit().multiply(BigDecimal.valueOf(Double.parseDouble(String.valueOf(invest.getInEarning())))));
                insertEarningReq.setEarnCurrent(BigDecimal.ZERO);
                insertEarningReq.setEarnPlayerId(player.getPlayerId());
                insertEarningReq.setEarnInvestId(invest.getInId());
                insertEarningReq.setInType(invest.getInType());
                insertEarningReq.setOrderId(order.getOrderId());
                insertEarningReq.setEarnEnterpriseTax(invest.getEnterpriseIntax());
                insertEarningReq.setEarnPersonalTax(invest.getPersonalInTax());
                insertEarningReq.setEarnQuotaTax(invest.getInQuotaTax());
                earningService.insertEarning(insertEarningReq);
            }

            //投资扣减用户账户金额
            Result updatePlayerAccountResult = null;
            //交易金额
            BigDecimal orderAmount = orderReq.getOrderAmount();
            //总税金
            BigDecimal inTax = invest.getPersonalInTax().add(invest.getEnterpriseIntax()).add(invest.getInQuotaTax());
            if (order != null && order.getOrderId() != null) {
                updatePlayerAccountResult = this.deductPlayerAccountAmount(orderAmount, inTax, playerAccount);
                success = updatePlayerAccountResult.getSuccess();
                desc = updatePlayerAccountResult.getMsg();
            } else {
                success = orderResult.getSuccess();
                desc = orderResult.getMsg();
            }

            //用户交易
            Result<PlayerTrade> playerTradeResult = null;
            PlayerTrade trade = null;
            if (updatePlayerAccountResult != null && updatePlayerAccountResult.getSuccess()) {
                trade = new PlayerTrade();
                trade.setTradeType(TradeType.INVEST.getCode());
                trade.setInOutStatus(AmountDynType.OUT.getCode());
                trade.setTradeStatus(TradeStatus.FREEZE.getCode());
                trade.setTradePlayerId(player.getPlayerId());
                trade.setTradeOrderId(order.getOrderId());
                trade.setTradeAccId(playerAccount.getAccId());
                trade.setTradeAmount(orderAmount);
                trade.setPersonalTax(invest.getPersonalInTax());
                trade.setEnterpriseTax(invest.getEnterpriseIntax());
                trade.setQuotaTax(invest.getInQuotaTax());
                trade.setTradeDesc("预约投资");
                playerTradeResult = tradeService.insertPlayerTrade(trade);
                trade = playerTradeResult.getData();
                success = playerTradeResult.getSuccess();
                desc = playerTradeResult.getMsg();
            }

            Result<TradeVerify> tradeVerifyResult = null;
            if (playerTradeResult != null && playerTradeResult.getSuccess()) {
                //生成投资待审核
                tradeVerifyResult = this.createTradeVerify(trade);

            }

            //交易流水
            if (playerTradeResult != null && playerTradeResult.getSuccess()) {
                TradeDetail tradeDetail = new TradeDetail();
                tradeDetail.setTradeId(trade.getTradeId());
                tradeDetail.setOrderId(order.getOrderId());
                tradeDetail.setPlayerId(player.getPlayerId());
                if (tradeVerifyResult != null && tradeVerifyResult.getData() != null
                        && tradeVerifyResult.getData().getVerifyId() != null) {
                    tradeDetail.setVerifyId(tradeVerifyResult.getData().getVerifyId());
                    tradeDetail.setVerifyTime(new Date());
                }
                //投资金额
                tradeDetail.setTradeAmount(orderAmount);
                tradeDetail.setUsdtSurplus(playerAccount.getAccUsdt().subtract(orderAmount));
                tradeDetail.setMtSurplus(playerAccount.getAccMt());
                tradeDetail.setTradeDetailType(TradeDetailType.USDT_INVEST_FREEZE.getCode());
                tradeDetail.setDescr(TradeDetailType.USDT_INVEST_FREEZE.getDesc());
                tradeService.insertTradeDetail(tradeDetail);
                //个人所得税
                if (trade.getPersonalTax().compareTo(BigDecimal.ZERO) > 0) {
                    tradeDetail.setTradeAmount(trade.getPersonalTax());
                    tradeDetail.setMtSurplus(playerAccount.getAccMt().subtract(trade.getPersonalTax()));
                    tradeDetail.setTradeDetailType(TradeDetailType.MT_INVEST_PERSONAL_TAX.getCode());
                    tradeDetail.setDescr(TradeDetailType.MT_INVEST_PERSONAL_TAX.getDesc());
                    tradeService.insertTradeDetail(tradeDetail);
                }
                //企业所得税
                if (trade.getEnterpriseTax().compareTo(BigDecimal.ZERO) > 0) {
                    tradeDetail.setTradeAmount(trade.getEnterpriseTax());
                    tradeDetail.setMtSurplus(playerAccount.getAccMt().subtract(trade.getEnterpriseTax().add(trade.getPersonalTax())));
                    tradeDetail.setTradeDetailType(TradeDetailType.MT_INVEST_ENTERPRISE_TAX.getCode());
                    tradeDetail.setDescr(TradeDetailType.MT_INVEST_ENTERPRISE_TAX.getDesc());
                    tradeService.insertTradeDetail(tradeDetail);
                }
                //定额得税
                if (trade.getQuotaTax().compareTo(BigDecimal.ZERO) > 0) {
                    tradeDetail.setTradeAmount(trade.getQuotaTax());
                    tradeDetail.setMtSurplus(playerAccount.getAccMt().subtract(trade.getEnterpriseTax().add(trade.getPersonalTax()).add(trade.getQuotaTax())));
                    tradeDetail.setTradeDetailType(TradeDetailType.MT_INVEST_QUOTA_TAX.getCode());
                    tradeDetail.setDescr(TradeDetailType.MT_INVEST_QUOTA_TAX.getDesc());
                    tradeService.insertTradeDetail(tradeDetail);
                }

                success = Boolean.TRUE;
                desc = "预约投资成功";
                msg.getData().setCode(ReturnStatus.SUCCESS.getStatus());
                result.put("usdtFreeze", trade.getTradeAmount());
                result.put("mtFreeze", invest.getPersonalInTax().add(invest.getEnterpriseIntax()).add(invest.getInQuotaTax()));
                result.put("state", ReturnStatus.INVEST_SUBSCRIBED.getStatus());
            } else {
                success = Boolean.FALSE;
                desc = "预约投资失败";
                msg.getData().setCode(ReturnStatus.ERROR.getStatus());
            }
        } catch (Exception e) {
            logger.error("预约投资异常", e);
        }

        msg.setDesc(desc);
        msg.getData().setData(result);
        msg.getData().setCode(ReturnStatus.SUCCESS.getStatus());
        return msg;
    }

    /**
     * 投资
     *
     * @param msg
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Message playerInvesting(Message msg) throws BusinessException {
        logger.info("投资:{}", msg);
        String desc = "";
        boolean success = Boolean.FALSE;
        MessageData messageData = new MessageData(msg.getData().getType(), msg.getData().getModel());
        msg.setDesc("投资失败");
        InvestOrderReq orderReq = DataUtils.getInvestOrderReqFromMessage(msg);
        //修改订单状态
        Result<Integer> playerInvestingResult = orderService.playerInvesting(orderReq.getOrderId());
        if (playerInvestingResult.getSuccess() && playerInvestingResult.getData() > 0) {
            //TODO
            //扣减冻结金额

            //扣减冻结金额流水

            //生产扣减冻结金额流水


            //平台账户进账

            //平台账户进账流水

            msg.setDesc("投资成功");
        }
        msg.setData(messageData);
        return msg;
    }


    /**
     * 获取当前投资项目数据
     *
     * @param inId
     * @param inName
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public InvestResp getInvestByIdOrinName(Integer inId, String inName, Integer inType) throws BusinessException {
        CityInvestReq investReq = new CityInvestReq();
        investReq.setInId(inId);
        investReq.setInName(inName);
        investReq.setInType(inType);
        Result<InvestResp> investResult = investService.getInvest(investReq);
        return investResult.getData();
    }


    /**
     * 生成订单
     *
     * @param invest
     * @param orderPayerId
     * @param desc
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Result<InvestOrder> orderCreate(InvestResp invest, String orderPayerId, int orderRepeat, String desc) throws BusinessException {
        Result<InvestOrder> orderResult = new Result<>();
        if (StringUtils.isEmpty(desc)) {
            InvestOrder recordReq = new InvestOrder();
            recordReq.setOrderInvestId(invest.getInId());
            recordReq.setOrderPayerId(orderPayerId);
            //已预约
            recordReq.setOrderState(InvestStatus.SUBSCRIBED.name());
            recordReq.setOrderRepeat(orderRepeat);
            recordReq.setOrderAmount(invest.getInLimit());
            recordReq.setOrderName(invest.getInName());
            recordReq.setOrderNum(KeyGenerator.getUUID());
            orderResult = orderService.insertOrder(recordReq);
        }
        return orderResult;
    }

    /**
     * 下单用户账户扣减金额
     * 冻结金额
     * 投资只能用usdt
     *
     * @param orderAmount
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Result deductPlayerAccountAmount(BigDecimal orderAmount,
                                            BigDecimal inTax,
                                            PlayerAccount playerAccount) throws BusinessException {
        String msg = "";
        BigDecimal usdtAvailable = playerAccount.getAccUsdtAvailable();
        BigDecimal mtAvailable = playerAccount.getAccMtAvailable();
        //usdt余额是否充足
        if (orderAmount.compareTo(usdtAvailable) > 0) {
            msg = "USDT不足";
        }
        //是否可扣税金
        if (inTax.compareTo(mtAvailable) > 0) {
            msg = "MT不足";
        }

        boolean success = Boolean.FALSE;
        if (StringUtils.isBlank(msg)) {
            //投资冻结USDT
            playerAccount.setAccUsdt(playerAccount.getAccUsdt().subtract(orderAmount));
            playerAccount.setAccUsdtAvailable(playerAccount.getAccUsdtAvailable().subtract(orderAmount));
            playerAccount.setAccUsdtFreeze(playerAccount.getAccUsdtFreeze().add(orderAmount));
            //投资冻结税金MT
            playerAccount.setAccMt(playerAccount.getAccUsdt().subtract(inTax));
            playerAccount.setAccMtAvailable(playerAccount.getAccMtAvailable().subtract(inTax));
            playerAccount.setAccMtFreeze(playerAccount.getAccMtFreeze().add(inTax));

            Result<Integer> updatePlayerAccountResult = accountService.updatePlayerAccount(playerAccount);

            if (updatePlayerAccountResult != null && updatePlayerAccountResult.getSuccess()
                    && updatePlayerAccountResult.getData() > 0) {
                msg = "投资预约成功！";
                success = Boolean.TRUE;
            } else {
                msg = "投资预约失败！";
                success = Boolean.FALSE;
            }
        }

        return new Result<>(success, msg);
    }

    /**
     * 用户交易流水
     *
     * @param playerId
     * @param orderId
     * @param tradeAmount
     * @param amountType
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Result<PlayerTrade> createPlayerTrade(String playerId, Integer orderId,
                                                 BigDecimal tradeAmount, String amountType,
                                                 String tradeType, String tradeStatus) throws BusinessException {
        PlayerTrade insertPlayerTradeReq = new PlayerTrade();
        insertPlayerTradeReq.setTradeOrderId(orderId);
        insertPlayerTradeReq.setTradeAmount(tradeAmount);
        insertPlayerTradeReq.setTradeType(tradeType);
        insertPlayerTradeReq.setTradePlayerId(playerId);
        insertPlayerTradeReq.setTradeDesc("玩家投资" + amountType);
        return tradeService.insertPlayerTrade(insertPlayerTradeReq);
    }

    /**
     * 生成订单待审核数据
     *
     * @param trade
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Result<TradeVerify> createTradeVerify(PlayerTrade trade) throws BusinessException {
        TradeVerify insertTradeVerifyReq = new TradeVerify();
        insertTradeVerifyReq.setVerifyOrderId(trade.getTradeOrderId());
        insertTradeVerifyReq.setVerifyTradeId(trade.getTradeId());
        insertTradeVerifyReq.setVerifyStatus(VerifyStatus.WAIT.name());
        insertTradeVerifyReq.setVerifyDesc(VerifyStatus.WAIT.getDesc());
        return verifyService.insertTradeVerify(insertTradeVerifyReq);
    }

    @LcnTransaction
    @Transactional
    @Override
    public Message getPlayerInvestOrderById(Message msg) throws BusinessException {
        logger.info("查询投资:{}", msg);
        MessageData messageData = new MessageData(msg.getData().getType(), msg.getData().getModel());
        PlayerResp player = commonService.getPlayerByUserName(msg);
        if (player == null) {
            messageData.setData(null);
            msg.setData(messageData);
            msg.setDesc("用户名或昵称不能为空");
            return msg;
        }

        InvestOrderReq orderReq = DataUtils.getInvestOrderReqFromMessage(msg);
        Result<InvestOrder> orderResult = orderService.getOrderById(orderReq.getOrderId());

        messageData.setData(JSON.toJSONString(orderResult.getData()));
        msg.setData(messageData);
        msg.setDesc(orderResult.getMsg());
        return msg;
    }

    @LcnTransaction
    @Transactional
    @Override
    public Message getPlayerInvestOrders(Message msg) throws BusinessException {
        logger.info("查询投资列表:{}", msg);
        InvestOrderReq orderReq = DataUtils.getInvestOrderReqFromMessage(msg);
        PlayerResp player = commonService.getPlayerByUserName(msg);
        return getPlayerOrFriendOrders(msg, orderReq, player);
    }

    @LcnTransaction
    @Transactional
    @Override
    public Message getFriendInvestOrders(Message msg) throws BusinessException {
        logger.info("查询好友投资列表:{}", msg);
        InvestOrderReq orderReq = DataUtils.getInvestOrderReqFromMessage(msg);
        PlayerResp friend = commonService.getFriendByNick(msg);
        return getPlayerOrFriendOrders(msg, orderReq, friend);
    }

    @LcnTransaction
    @Transactional
    @Override
    public Message getPlayerOrFriendOrders(Message msg, InvestOrderReq orderReq, PlayerResp player) throws BusinessException {
        InvestOrderReq getOrdersReq = new InvestOrderReq();
        getOrdersReq.setOrderId(orderReq.getInvestId());
        getOrdersReq.setPlayerId(player.getPlayerId());
        getOrdersReq.setInvestId(orderReq.getInvestId());
        getOrdersReq.setOrderState(orderReq.getOrderState());
        getOrdersReq.setOrderRepeat(orderReq.getOrderRepeat());
        Result<List<InvestOrderResp>> ordersResult = orderService.getOrders(getOrdersReq);

        List<InvestOrderResp> orderList = ordersResult.getData();
        List<Map> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(orderList)) {
            Map<String, Object> map = null;
            CityInvestReq getInvest = null;
            for (InvestOrderResp order : orderList) {
                map = new HashMap<>();
                getInvest = new CityInvestReq();
                getInvest.setInId(order.getOrderId());
                Result<InvestResp> investResult = investService.getInvest(getInvest);
                InvestResp invest = investResult.getData();

                map.put("username", player.getPlayerName());
                map.put("inImg", invest.getInImg());
                map.put("inName", invest.getInName());
                map.put("inId", invest.getInId());
                map.put("profit", invest.getPersonalInTax());
                map.put("orderAmount", invest.getInLimit());
                map.put("personalInTax", invest.getPersonalInTax());
                map.put("enterpriseIntax", invest.getEnterpriseIntax());
                map.put("status", 0); //TODO
                list.add(map);
            }
        }
        /*personalInTax	个人所得税
        enterpriseIntax	企业所得税
        profit	获利*/

        MessageData messageData = new MessageData(msg.getData().getType(), msg.getData().getModel());
        messageData.setData(JSON.toJSONString(list));
        msg.setData(messageData);
        msg.setDesc(ordersResult.getMsg());
        return msg;
    }


    @LcnTransaction
    @Transactional
    @Override
    public Message playerInvestInvalid(Message msg) throws BusinessException {
        logger.info("投资作废:{}", msg);
        InvestOrderReq orderReq = DataUtils.getInvestOrderReqFromMessage(msg);
        //物业扣减金额返回


        //生成订单作废


        //下单用户账户扣减金额返回


        //用户交易流水


        return null;
    }

    @LcnTransaction
    @Transactional
    @Override
    public Message playerInvestCancel(Message msg) throws BusinessException {
        logger.info("玩家取消投资:{}", msg);

        //物业扣减金额返回


        //生成订单作废


        //下单用户账户扣减金额返回


        //用户交易流水


        return null;
    }
}
