package com.dream.city.invest.service;

import com.dream.city.base.model.entity.PlayerTrade;
import com.dream.city.base.model.req.PlayerTradeReq;
import com.dream.city.base.model.resp.PlayerTradeResp;

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

}
