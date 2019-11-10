package com.dream.city.service;

import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.base.model.entity.PlayerTrade;
import com.dream.city.base.model.entity.RuleItem;
import com.dream.city.base.model.entity.TradeDetail;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Wvv
 */
public interface TradeService {

    Integer addTrade(PlayerTrade trade);

    void addTradeDetail(TradeDetail tradeDetail);

    PlayerTrade getPlayerTrade(Integer id);
}
