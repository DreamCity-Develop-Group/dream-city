package com.dream.city.service.impl;

import com.alibaba.fastjson.JSON;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.*;
import com.dream.city.base.model.enu.AmountDynType;
import com.dream.city.base.model.enu.AmountType;
import com.dream.city.base.model.enu.OrderState;
import com.dream.city.base.model.enu.VerifyStatus;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        //校验项目投资规则 TODO
        CityInvest investReq = new CityInvest();
        investReq.setInId(orderReq.getInvestId());
        investReq.setInId(orderReq.getInvestId());
        investReq.setInName(orderReq.getInName());
        Result<CityInvest> investResult = propertyService.getInvest(investReq);
        CityInvest invest = investResult.getData();
        String desc = null;
        //限额
        if (orderReq.getOrderAmount().compareTo(invest.getInLimit())>0){
            desc = "超过项目投资限额：" + invest.getInLimit();
        }

        //生成订单
        Result<InvestOrder> orderResult = null;
        InvestOrder order = null;
        if (StringUtils.isEmpty(desc)){
            InvestOrder recordReq =new InvestOrder();
            recordReq.setOrderInvestId(invest.getInId());
            recordReq.setOrderPayerId(orderReq.getPayerId());
            recordReq.setOrderState(OrderState.WAITVERIFY.name());
            recordReq.setOrderRepeat(0);
            orderResult = orderService.insertOrder(recordReq);
            order = orderResult.getData();
        }

        //下单用户账户扣减金额
        Result<Integer> updatePlayerAccountResult = null;
        String amountType = null;
        if (orderResult != null && order != null && order.getOrderId() != null){
            //updatePlayerAccount TODO
            PlayerAccount getPlayerAccount = new PlayerAccount();
            getPlayerAccount.setAccPlayerId(orderReq.getPayerId());
            Result<PlayerAccount> playerAccountResult = accountService.getPlayerAccount(getPlayerAccount);

            PlayerAccount playerAccount = new PlayerAccount();
            if (playerAccountResult.getSuccess() && playerAccountResult.getData() != null){
                playerAccount.setAccId(playerAccountResult.getData().getAccId());
                playerAccount.setAccPlayerId(playerAccountResult.getData().getAccPlayerId());
            }
            if (orderReq.getAmountType().equalsIgnoreCase(AmountType.mt.name())){
                amountType = AmountType.mt.name();
                playerAccount.setAccMt(playerAccount.getAccMt().subtract(orderReq.getOrderAmount()));
            }
            if (orderReq.getAmountType().equalsIgnoreCase(AmountType.usdt.name())){
                amountType = AmountType.usdt.name();
                playerAccount.setAccUsdt(playerAccount.getAccUsdt().subtract(orderReq.getOrderAmount()));
            }
            updatePlayerAccountResult = accountService.updatePlayerAccount(playerAccount);
        }else {
            desc = orderResult.getMsg();
        }

        //用户交易流水
        Result<PlayerTrade> playerTradeResult = null;
        PlayerTrade trade = null;
        if (updatePlayerAccountResult != null && updatePlayerAccountResult.getSuccess()
                && updatePlayerAccountResult.getData() > 0){
            PlayerTrade insertPlayerTradeReq = new PlayerTrade();
            insertPlayerTradeReq.setTradeOrderId(order.getOrderId());
            insertPlayerTradeReq.setTradeAmount(orderReq.getOrderAmount());
            insertPlayerTradeReq.setTradeAmountType(amountType);
            insertPlayerTradeReq.setTradeType(AmountDynType.out.name());
            insertPlayerTradeReq.setTradePlayerId(orderReq.getPayerId());
            insertPlayerTradeReq.setTradeDesc("玩家投资"+amountType);
            playerTradeResult = tradeService.insertPlayerTrade(insertPlayerTradeReq);
            trade = playerTradeResult.getData();
        }else {
            desc = updatePlayerAccountResult.getMsg();
        }

        //生成审核
        Result<Integer> tradeVerifyResult = null;
        if (playerTradeResult != null && playerTradeResult.getSuccess() && trade != null){
            TradeVerify insertTradeVerifyReq = new TradeVerify();
            insertTradeVerifyReq.setVerifyTradeId(trade.getTradeId());
            insertTradeVerifyReq.setVerifyAmount(trade.getTradeAmount());
            insertTradeVerifyReq.setVerifyStatus(VerifyStatus.wait.name());
            //insertTradeVerifyReq.setVerifyEmpId();//审核人id TODO
            insertTradeVerifyReq.setVerifyDesc(VerifyStatus.wait.getDesc());
            tradeVerifyResult = verifyService.insertTradeVerify(insertTradeVerifyReq);
        }else {
            desc = playerTradeResult.getMsg();
        }
        if (tradeVerifyResult == null || (tradeVerifyResult != null && !tradeVerifyResult.getSuccess())
                || (tradeVerifyResult != null && tradeVerifyResult.getData() == null)
                || (tradeVerifyResult != null && tradeVerifyResult.getData() != null && tradeVerifyResult.getData() < 1)){
            desc = tradeVerifyResult.getMsg();
        }

        msg.setDesc(desc);
        msg.getData().setData(desc);
        return msg;
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
