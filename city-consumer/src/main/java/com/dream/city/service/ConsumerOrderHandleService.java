package com.dream.city.service;

import com.dream.city.base.model.Message;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 玩家投资
 * 物业投资
 */
public interface ConsumerOrderHandleService {


    /**
     * 预约投资
     * @param msg
     * @return
     */
    @RequestMapping("/insertOrder")
    Message playerInvest(Message msg);


    @RequestMapping("/playerInvesting")
    Message playerInvesting(Message msg);

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
     * 查询投资
     * @param msg
     * @return
     */
    @RequestMapping("/getOrderById/{orderId}")
    Message getPlayerInvestOrderById(Message msg);

    /**
     * 投资列表
     * @param msg
     * @return
     */
    @RequestMapping("/getOrders")
    Message getPlayerInvestOrders(Message msg);


    /**
     * 好友投资列表
     * @param msg
     * @return
     */
    @RequestMapping("/getOrders")
    Message getFriendInvestOrders(Message msg);



}
