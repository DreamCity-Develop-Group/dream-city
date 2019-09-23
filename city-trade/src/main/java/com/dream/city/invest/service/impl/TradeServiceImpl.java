package com.dream.city.invest.service.impl;


import com.dream.city.base.model.entity.PlayerTrade;
import com.dream.city.invest.domain.mapper.PlayerTradeMapper;
import com.dream.city.invest.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TradeServiceImpl implements TradeService {


    @Autowired
    PlayerTradeMapper tradeMapper;



    @Override
    @Transactional
    public int insertPlayerTrade(PlayerTrade record) {
        Integer integer = tradeMapper.insertSelective(record);
        return integer ==null?0:integer;
    }

    @Override
    @Transactional
    public int updatePlayerTrade(PlayerTrade record) {
        Integer integer = tradeMapper.updateByPrimaryKeySelective(record);
        return integer ==null?0:integer;
    }

    @Override
    public PlayerTrade getPlayerTrade(Integer dynId) {
        if (dynId == null){
            return null;
        }
        return tradeMapper.getPlayerTrade(dynId);
    }

    @Override
    public List<PlayerTrade> getPlayerTradeList(PlayerTrade record) {
        return tradeMapper.getPlayerTradeList(record);
    }
}
