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
    boolean playerRecharge(Message msg);

    /**
     * 玩家提现
     * @return
     */
    boolean playerWithdraw(Message msg);

    /**
     * 玩家转账
     * @return
     */
    boolean playerTransfer(Message msg);

    /**
     * 玩家投资
     * 购买mt
     * @return
     */
    boolean playerInvest(Message msg);

}
