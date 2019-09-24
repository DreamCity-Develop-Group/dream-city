package com.dream.city.service.impl;

import com.alibaba.fastjson.JSON;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.InvestOrder;
import com.dream.city.base.model.req.InvestOrderReq;
import com.dream.city.base.model.resp.PlayerResp;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.service.*;
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
    private ConsumerCommonsService commonsService;




    @Override
    public Message playerInvest(Message msg) {
        logger.info("玩家投资:{}",msg);
        //校验项目投资规则


        //物业扣减金额


        //生成订单


        //下单用户账户扣减金额

        //用户交易流水


        return null;
    }

    @Override
    public Message playerInvestInvalid(Message msg) {
        logger.info("投资作废:{}",msg);

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

    @Override
    public Message getplayerInvestOrderById(Message msg) {
        logger.info("查询订单:{}",msg);
        InvestOrderReq orderReq = DataUtils.getInvestOrderReqFromMessage(msg);
        Result<InvestOrder> orderResult = orderService.getOrderById(orderReq.getOrderId());
        MessageData messageData = new MessageData(msg.getData().getType(),msg.getData().getModel());
        messageData.setData(JSON.toJSONString(orderResult.getData()));
        msg.setData(messageData);
        msg.setDesc(orderResult.getMsg());
        return msg;
    }

    @Override
    public Message getplayerInvestOrders(Message msg) {
        logger.info("查询订单列表:{}",msg);
        InvestOrderReq orderReq = DataUtils.getInvestOrderReqFromMessage(msg);
        PlayerResp player = commonsService.getPlayerByNameOrNicke(msg);

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
}
