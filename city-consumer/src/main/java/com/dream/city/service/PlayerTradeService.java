package com.dream.city.service;

import com.dream.city.base.model.Message;

/**
 *  玩家交易
 */
public interface PlayerTradeService {

    /**
     * 玩家充值
     * @return
     */
    Message playerRecharge(Message msg);

    /**
     * 玩家提现
     * @return
     */
    Message playerWithdraw(Message msg);

    /**
     * 玩家转账
     * @return
     */
    Message playerTransfer(Message msg);

    /**
     * 玩家投资
     * 购买mt
     * @return
     */
    Message playerInvest(Message msg);

}
