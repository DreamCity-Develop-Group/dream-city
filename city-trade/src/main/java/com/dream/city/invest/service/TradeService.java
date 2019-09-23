package com.dream.city.invest.service;

import com.dream.city.base.model.entity.PlayerTrade;

import java.util.List;

/**
 * 交易记录
 */
public interface TradeService {

    int insertPlayerTrade(PlayerTrade record);

    int updatePlayerTrade(PlayerTrade record);

    PlayerTrade getPlayerTrade(Integer dynId);

    List<PlayerTrade> getPlayerTradeList(PlayerTrade record);

}
