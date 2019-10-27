package com.dream.city.service.handler;

import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;

public interface TradeService {

     Message getTradeDetailList(Message msg)throws BusinessException;


    /**
     * 根据tradeId获取投资记录
     * @param msg
     * @return
     */
     Message getPlayerTradeById(Message msg)throws BusinessException;


    /**
     * 获取投资记录
     * @param msg
     * @return
     */
     Message getPlayerTrade(Message msg)throws BusinessException ;

    /**
     * 获取投资记录列表
     * @param msg
     * @return
     */
     Message getPlayerTradeList(Message msg)throws BusinessException;


    /**
     * 玩家充值
     * @return
     */
     Message playerRecharge(Message msg)throws BusinessException;


    /**
     * 玩家提现
     * @return
     */
     Message playerWithdraw(Message msg)throws BusinessException ;


    /**
     * 玩家转账
     * @return
     */
     Message playerTransfer(Message msg)throws BusinessException;
}
