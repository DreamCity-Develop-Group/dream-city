package com.dream.city.invest.service;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.PlayerTrade;
import com.dream.city.base.model.entity.TradeVerify;
import com.dream.city.base.model.req.PlayerAccountReq;
import com.dream.city.base.model.req.PlayerTradeReq;
import com.dream.city.base.model.resp.PlayerAccountResp;
import com.dream.city.base.model.resp.PlayerTradeResp;

import java.math.BigDecimal;
import java.util.List;

/**
 * 交易记录
 */
public interface PlayerTradeService {

    PlayerTrade insertPlayerTrade(PlayerTrade record);

    int updatePlayerTrade(PlayerTrade record);

    PlayerTradeResp getPlayerTradeById(Integer tradeId);

    PlayerTrade getPlayerTrade(PlayerTrade record);

    List<PlayerTradeResp> getPlayerTradeList(PlayerTradeReq record);


    /**
     * 玩家充值
     * @return
     */
    Result<PlayerTrade> playerRecharge(PlayerAccountReq record);

    /**
     * 玩家提现
     * @return
     */
    Result<PlayerTrade>  playerWithdraw(PlayerAccountReq record);

    Result checkAmount(PlayerAccountReq record, PlayerAccount getPlayerAccount);


    /**
     * 玩家转账
     * @return
     */
    Result<PlayerTrade>  playerTransfer(PlayerAccountReq record);

    PlayerAccountResp getrAccountByAccAddr(String accAddr);

    void createTradeDetail(String playerId, Integer tradeId, BigDecimal tradeAmount, BigDecimal usdtSurplus,
                           BigDecimal mtSurplus, Integer orderId, Integer verifyId,
                           String tradeDetailType, String descr);

    Result<BigDecimal> updatePlayerAccount(PlayerAccountReq record);

    Result<PlayerTrade> createPlayerTrade(PlayerAccountReq record,BigDecimal tradeAmount,String desc);

    TradeVerify createTradeVerify(String toAddr,Integer tradeId, String verifyStatus, String verifyDesc);

}
