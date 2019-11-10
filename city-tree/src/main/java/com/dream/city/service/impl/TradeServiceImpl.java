package com.dream.city.service.impl;

import com.dream.city.base.model.entity.PlayerTrade;
import com.dream.city.base.model.entity.TradeDetail;
import com.dream.city.base.model.mapper.PlayerTradeMapper;
import com.dream.city.base.model.mapper.TradeDetailMapper;
import com.dream.city.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeServiceImpl implements TradeService {
    @Autowired
    PlayerTradeMapper tradeMapper;
    @Autowired
    TradeDetailMapper tradeDetailMapper;

    @Override
    public Integer addTrade(PlayerTrade trade) {
        return tradeMapper.insertSelective(trade);
    }

    @Override
    public void addTradeDetail(TradeDetail tradeDetail) {
        tradeDetailMapper.insertSelective(tradeDetail);
    }

    @Override
    public PlayerTrade getPlayerTrade(Integer id) {
        return tradeMapper.getPlayerTradeByOrderId();
    }
}
