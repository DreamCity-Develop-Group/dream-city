package com.dream.city.invest.service;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.req.PlayerAccountReq;

/**
 *  玩家交易
 */
public interface PlayerTradeService {

    /**
     * 玩家充值
     * @return
     */
    Result playerRecharge(PlayerAccountReq record);

    /**
     * 玩家提现
     * @return
     */
    Result playerWithdraw(PlayerAccountReq record);

    /**
     * 玩家转账
     * @return
     */
    Result playerTransfer(PlayerAccountReq record);


}
