package com.dream.city.invest.service.impl;


import com.dream.city.base.model.entity.PlayerTrade;
import com.dream.city.base.model.mapper.PlayerTradeMapper;
import com.dream.city.base.model.req.PlayerTradeReq;
import com.dream.city.base.model.resp.PlayerTradeResp;
import com.dream.city.invest.service.PlayerTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayerTradeServiceImpl implements PlayerTradeService {


    @Autowired
    PlayerTradeMapper tradeMapper;



    @Override
    @Transactional
    public PlayerTrade insertPlayerTrade(PlayerTrade record) {
        Integer integer = tradeMapper.insertSelective(record);
        if (integer == null || integer < 1){
            return null;
        }
        return record;
    }

    @Override
    @Transactional
    public int updatePlayerTrade(PlayerTrade record) {
        Integer integer = tradeMapper.updateByPrimaryKeySelective(record);
        return integer ==null?0:integer;
    }

    @Override
    public PlayerTradeResp getPlayerTradeById(Integer dynId) {
        if (dynId == null){
            return null;
        }
        return tradeMapper.getPlayerTradeById(dynId);
    }

    @Override
    public PlayerTrade getPlayerTrade(PlayerTrade record) {
        return tradeMapper.getPlayerTrade(record);
    }

    @Override
    public List<PlayerTradeResp> getPlayerTradeList(PlayerTradeReq record) {
        return tradeMapper.getPlayerTradeList(record);
    }
}
