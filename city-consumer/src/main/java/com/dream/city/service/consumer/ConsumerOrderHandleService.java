package com.dream.city.service.consumer;

import com.dream.city.base.model.Message;


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
    Message playerInvest(Message msg);


    Message playerInvesting(Message msg);

    /**
     * 投资作废
     * @param msg
     * @return
     */
    Message playerInvestInvalid(Message msg);

    /**
     *  玩家取消投资
     * @param msg
     * @return
     */
    Message playerInvestCancel(Message msg);

    /**
     * 查询投资
     * @param msg
     * @return
     */
    Message getPlayerInvestOrderById(Message msg);

    /**
     * 投资列表
     * @param msg
     * @return
     */
    Message getPlayerInvestOrders(Message msg);


    /**
     * 好友投资列表
     * @param msg
     * @return
     */
    Message getFriendInvestOrders(Message msg);



}
