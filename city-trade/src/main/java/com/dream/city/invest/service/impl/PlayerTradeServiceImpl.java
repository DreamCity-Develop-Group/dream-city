package com.dream.city.invest.service.impl;


import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.PlayerTrade;
import com.dream.city.base.model.mapper.PlayerTradeMapper;
import com.dream.city.base.model.req.PlayerTradeReq;
import com.dream.city.base.model.resp.PlayerTradeResp;
import com.dream.city.invest.service.PlayerTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayerTradeServiceImpl implements PlayerTradeService {


    @Autowired
    PlayerTradeMapper tradeMapper;



    @LcnTransaction
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PlayerTrade insertPlayerTrade(PlayerTrade record) throws BusinessException {
        Integer integer = tradeMapper.insertSelective(record);
        if (integer == null || integer < 1){
            return null;
        }
        return record;
    }

    @LcnTransaction
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int updatePlayerTrade(PlayerTrade record)  throws BusinessException{
        Integer integer = tradeMapper.updateByPrimaryKeySelective(record);
        return integer ==null?0:integer;
    }

    @LcnTransaction
    @Transactional
    @Override
    public PlayerTradeResp getPlayerTradeById(Integer dynId) throws BusinessException {
        if (dynId == null){
            return null;
        }
        return tradeMapper.getPlayerTradeById(dynId);
    }

    @LcnTransaction
    @Transactional
    @Override
    public PlayerTrade getPlayerTrade(PlayerTrade record) throws BusinessException {
        return tradeMapper.getPlayerTrade(record);
    }

    @LcnTransaction
    @Transactional
    @Override
    public List<PlayerTradeResp> getPlayerTradeList(PlayerTradeReq record)  throws BusinessException{
        return tradeMapper.getPlayerTradeList(record);
    }
}
