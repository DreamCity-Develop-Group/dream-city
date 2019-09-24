package com.dream.city.service;

import com.dream.city.base.model.Message;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 玩家投资
 * 物业投资
 */
public interface ConsumerOrderHandleService {


    /**
     * 玩家投资
     * @param msg
     * @return
     */
    @RequestMapping("/insertOrder")
    Message playerInvest(Message msg);

    /**
     * 投资作废
     * @param msg
     * @return
     */
    @RequestMapping("/orderInvalid")
    Message playerInvestInvalid(Message msg);

    /**
     *  玩家取消投资
     * @param msg
     * @return
     */
    @RequestMapping("/orderCancel")
    Message playerInvestCancel(Message msg);

    /**
     * 查询订单
     * @param msg
     * @return
     */
    @RequestMapping("/getOrderById/{orderId}")
    Message getplayerInvestOrderById(Message msg);

    /**
     * 订单列表
     * @param msg
     * @return
     */
    @RequestMapping("/getOrders")
    Message getplayerInvestOrders(Message msg);



}
