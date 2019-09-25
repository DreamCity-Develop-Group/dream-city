package com.dream.city.service.impl;

import com.alibaba.fastjson.JSON;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.*;
import com.dream.city.base.model.enu.*;
import com.dream.city.base.model.req.InvestOrderReq;
import com.dream.city.base.model.resp.PlayerResp;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.service.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ConsumerOrderHandleServiceImpl implements ConsumerOrderHandleService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConsumerOrderService orderService;
    @Autowired
    private ConsumerPropertyService propertyService;
    @Autowired
    private ConsumerAccountService accountService;
    @Autowired
    private ConsumerTradeService tradeService;
    @Autowired
    private ConsumerTradeVerifyService verifyService;
    @Autowired
    private ConsumerCommonsService commonsService;




    @Override
    public Message playerInvest(Message msg) {
        logger.info("玩家投资:{}",msg);
        InvestOrderReq orderReq = DataUtils.getInvestOrderReqFromMessage(msg);

        //获取项目数据
        CityInvest invest = getInvestByIdOrinName(orderReq.getInvestId(),orderReq.getInName());
        //获取当前时间  后改为数据库时间 TODO
        Date investTime = new Date();

        String desc = null;
        //校验项目投资规则
        //1是否复投
        int orderRepeat = 0;
        InvestOrder getOrdersReq = new InvestOrder();
        getOrdersReq.setOrderInvestId(orderReq.getInvestId());
        getOrdersReq.setOrderPayerId(orderReq.getPayerId());
        Result<List<InvestOrder>> getOrdersResult = orderService.getOrders(getOrdersReq);
        List<InvestOrder> orderList= getOrdersResult.getData();
        if (orderList != null && orderList.size() > 0){
            orderRepeat = 1;
        }
        //2项目开始投资时间
        if (investTime.before(invest.getInStart())){
            desc = "项目还未开始投资";
        }
        //3项目结束投资时间
        if (investTime.after(invest.getInEnd())){
            desc = "项目投资已结束";
        }
        //4限额
        if (orderReq.getOrderAmount().compareTo(invest.getInLimit())>0){
            desc = "超过项目投资限额，投资限额为：" + invest.getInLimit();
        }

        //生成订单
        Result<InvestOrder> orderResult = this.orderCreate(invest.getInId(),orderReq.getPayerId(),orderRepeat,desc);
        InvestOrder order = orderResult.getData();

        //下单用户账户扣减金额
        Map<String,String> updatePlayerAccountResult = null;
        boolean updatePlayerAccountSuccess = Boolean.FALSE;
        int updatePlayerAccountDate = 0;
        String updatePlayerAccountMsg = "";
        String amountType = "";
        String tradeAmountType = "";
        if (order != null && order.getOrderId() != null){
            updatePlayerAccountResult = this.deductPlayerAccountAmount(orderReq);
            amountType = updatePlayerAccountResult.get("amountType");
            tradeAmountType = updatePlayerAccountResult.get("tradeType");
            updatePlayerAccountDate = Integer.parseInt(updatePlayerAccountResult.get("data"));
            updatePlayerAccountSuccess = Boolean.parseBoolean(updatePlayerAccountResult.get("success"));
            updatePlayerAccountMsg = updatePlayerAccountResult.get("msg");
        }else {
            desc = orderResult.getMsg();
        }

        //用户交易流水
        Result<PlayerTrade> playerTradeResult = null;
        PlayerTrade trade = null;
        if (updatePlayerAccountResult != null && updatePlayerAccountSuccess && updatePlayerAccountDate > 0){
            playerTradeResult = this.createPlayerTrade(orderReq.getPayerId(), order.getOrderId(),
                    orderReq.getOrderAmount(), amountType, tradeAmountType);
            trade = playerTradeResult.getData();
        }else {
            desc = updatePlayerAccountMsg;
        }

        //冻结扣金 从账户冻结，提取成功后直接扣除税金 TODO

        //生成待审核
        Result<Integer> tradeVerifyResult = null;
        if (playerTradeResult != null && playerTradeResult.getSuccess() && trade != null){
            tradeVerifyResult = this.createTradeVerify(trade);
        }else {
            desc = playerTradeResult.getMsg();
        }
        if (tradeVerifyResult == null || !tradeVerifyResult.getSuccess()
                || tradeVerifyResult.getData() == null
                || (tradeVerifyResult.getData() != null && tradeVerifyResult.getData() < 1)){
            desc = tradeVerifyResult.getMsg();
        }

        msg.setDesc(desc);
        msg.getData().setData(desc);
        return msg;
    }


    /**
     * 获取当前投资项目数据
     * @param inId
     * @param inName
     * @return
     */
    private CityInvest getInvestByIdOrinName(Integer inId,String inName){
        CityInvest investReq = new CityInvest();
        investReq.setInId(inId);
        investReq.setInName(inName);
        Result<CityInvest> investResult = propertyService.getInvest(investReq);
        return  investResult.getData();
    }


    /**
     * 生成订单
     * @param investId
     * @param orderPayerId
     * @param desc
     * @return
     */
    private Result<InvestOrder> orderCreate(Integer investId,String orderPayerId,int orderRepeat,String desc){
        Result<InvestOrder> orderResult = null;
        if (StringUtils.isEmpty(desc)){
            InvestOrder recordReq =new InvestOrder();
            recordReq.setOrderInvestId(investId);
            recordReq.setOrderPayerId(orderPayerId);
            recordReq.setOrderState(OrderState.WAITVERIFY.name());
            recordReq.setOrderRepeat(orderRepeat);
            orderResult = orderService.insertOrder(recordReq);
        }
        return orderResult;
    }

    /**
     * 下单用户账户扣减金额
     * @param orderReq
     * @return
     */
    private Map<String,String> deductPlayerAccountAmount(InvestOrderReq orderReq){
        String amountType = "";
        String tradeAmountType = "";
        PlayerAccount getPlayerAccount = new PlayerAccount();
        getPlayerAccount.setAccPlayerId(orderReq.getPayerId());
        //updatePlayerAccount TODO
        Result<PlayerAccount> playerAccountResult = accountService.getPlayerAccount(getPlayerAccount);

        PlayerAccount playerAccount = new PlayerAccount();
        if (playerAccountResult.getSuccess() && playerAccountResult.getData() != null){
            playerAccount.setAccId(playerAccountResult.getData().getAccId());
            playerAccount.setAccPlayerId(playerAccountResult.getData().getAccPlayerId());
        }
        if (orderReq.getAmountType().equalsIgnoreCase(AmountType.mt.name())){
            amountType = AmountType.mt.name();
            tradeAmountType = TradeType.MTINVEST.getCode();
            playerAccount.setAccMt(playerAccount.getAccMt().subtract(orderReq.getOrderAmount()));
        }
        if (orderReq.getAmountType().equalsIgnoreCase(AmountType.usdt.name())){
            amountType = AmountType.usdt.name();
            tradeAmountType = TradeType.MTINVEST.getCode();
            playerAccount.setAccUsdt(playerAccount.getAccUsdt().subtract(orderReq.getOrderAmount()));
        }
        Result<Integer> updatePlayerAccountResult = accountService.updatePlayerAccount(playerAccount);
        String success = "";
        String data = "";
        String msg = updatePlayerAccountResult.getMsg();
        if (updatePlayerAccountResult != null){
            success=String.valueOf(updatePlayerAccountResult.getSuccess());
            data = String.valueOf(updatePlayerAccountResult.getData());
        }

        Map<String,String> result = new HashMap<>();
        result.put("amountType",amountType);
        result.put("tradeAmountType",tradeAmountType);
        result.put("data",data);
        result.put("msg",msg);
        result.put("success",success);
        return result;
    }

    /**
     * 用户交易流水
     * @param playerId
     * @param orderId
     * @param tradeAmount
     * @param amountType
     * @return
     */
    private Result<PlayerTrade> createPlayerTrade(String playerId,Integer orderId, BigDecimal tradeAmount,String amountType,String tradeAmountType){
        PlayerTrade insertPlayerTradeReq = new PlayerTrade();
        insertPlayerTradeReq.setTradeOrderId(orderId);
        insertPlayerTradeReq.setTradeAmount(tradeAmount);
        insertPlayerTradeReq.setTradeAmountType(tradeAmountType);
        insertPlayerTradeReq.setTradeType(AmountDynType.out.name());
        insertPlayerTradeReq.setTradePlayerId(playerId);
        insertPlayerTradeReq.setTradeDesc("玩家投资"+amountType);
        return tradeService.insertPlayerTrade(insertPlayerTradeReq);
    }

    /**
     * 生成订单待审核数据
     * @param trade
     * @return
     */
    private Result<Integer> createTradeVerify(PlayerTrade trade){
        TradeVerify insertTradeVerifyReq = new TradeVerify();
        insertTradeVerifyReq.setVerifyTradeId(trade.getTradeId());
        insertTradeVerifyReq.setVerifyAmount(trade.getTradeAmount());
        insertTradeVerifyReq.setVerifyStatus(VerifyStatus.wait.name());
        //insertTradeVerifyReq.setVerifyEmpId();//审核人id TODO
        insertTradeVerifyReq.setVerifyDesc(VerifyStatus.wait.getDesc());
        return verifyService.insertTradeVerify(insertTradeVerifyReq);
    }

    @Override
    public Message getPlayerInvestOrderById(Message msg) {
        logger.info("查询投资:{}",msg);
        MessageData messageData = new MessageData(msg.getData().getType(),msg.getData().getModel());
        PlayerResp player = commonsService.getPlayerByUserName(msg);
        if (player == null){
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

    @Override
    public Message getPlayerInvestOrders(Message msg) {
        logger.info("查询投资列表:{}",msg);
        InvestOrderReq orderReq = DataUtils.getInvestOrderReqFromMessage(msg);
        PlayerResp player = commonsService.getPlayerByUserName(msg);

        InvestOrder record = new InvestOrder();
        record.setOrderId(orderReq.getOrderId());
        record.setOrderInvestId(orderReq.getInvestId());
        record.setOrderPayerId(player.getPlayerId());
        record.setOrderState(orderReq.getOrderState());
        record.setOrderRepeat(orderReq.getOrderRepeat());
        Result<List<InvestOrder>> ordersResult = orderService.getOrders(record);

        List<InvestOrder> orderList = ordersResult.getData();
        List<Map> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(orderList)){
            Map map = null;
            for (InvestOrder order: orderList){
                map = JSON.parseObject(JSON.toJSONString(order));
                map.put("username",player.getPlayerName());
                list.add(map);
            }
        }

        MessageData messageData = new MessageData(msg.getData().getType(),msg.getData().getModel());
        messageData.setData(JSON.toJSONString(list));
        msg.setData(messageData);
        msg.setDesc(ordersResult.getMsg());
        return msg;
    }

    @Override
    public Message getFriendInvestOrders(Message msg) {
        logger.info("查询好友投资列表:{}",msg);
        InvestOrderReq orderReq = DataUtils.getInvestOrderReqFromMessage(msg);
        PlayerResp friend = commonsService.getFriendByNick(msg);

        InvestOrder record = new InvestOrder();
        record.setOrderId(orderReq.getOrderId());
        record.setOrderInvestId(orderReq.getInvestId());
        record.setOrderPayerId(friend.getPlayerId());
        record.setOrderState(orderReq.getOrderState());
        record.setOrderRepeat(orderReq.getOrderRepeat());
        Result<List<InvestOrder>> ordersResult = orderService.getOrders(record);

        List<InvestOrder> orderList = ordersResult.getData();
        List<Map> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(orderList)){
            Map map = null;
            for (InvestOrder order: orderList){
                map = JSON.parseObject(JSON.toJSONString(order));
                map.put("username",friend.getPlayerName());
                list.add(map);
            }
        }

        MessageData messageData = new MessageData(msg.getData().getType(),msg.getData().getModel());
        messageData.setData(JSON.toJSONString(list));
        msg.setData(messageData);
        msg.setDesc(ordersResult.getMsg());
        return msg;
    }


    @Override
    public Message playerInvestInvalid(Message msg) {
        logger.info("投资作废:{}",msg);
        InvestOrderReq orderReq = DataUtils.getInvestOrderReqFromMessage(msg);
        //物业扣减金额返回


        //生成订单作废


        //下单用户账户扣减金额返回


        //用户交易流水


        return null;
    }

    @Override
    public Message playerInvestCancel(Message msg) {
        logger.info("玩家取消投资:{}",msg);

        //物业扣减金额返回


        //生成订单作废


        //下单用户账户扣减金额返回


        //用户交易流水




        return null;
    }


}
