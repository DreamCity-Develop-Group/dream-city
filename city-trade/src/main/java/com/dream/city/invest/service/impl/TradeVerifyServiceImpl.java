package com.dream.city.invest.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.TradeVerify;
import com.dream.city.base.model.mapper.TradeVerifyMapper;
import com.dream.city.invest.service.TradeVerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TradeVerifyServiceImpl implements TradeVerifyService {

    @Autowired
    TradeVerifyMapper verifyMapper;

    @LcnTransaction
    @Transactional
    @Override
    public TradeVerify insertTradeVerify(TradeVerify record) throws BusinessException{
        Integer integer = verifyMapper.insertSelective(record);
        return (integer == null || integer < 1)? null: record;
    }

    @LcnTransaction
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int updateTradeVerify(TradeVerify record)throws BusinessException {
        Integer integer = verifyMapper.updateByPrimaryKeySelective(record);
        return integer == null? 0: integer;
    }

    @LcnTransaction
    @Transactional
    @Override
    public TradeVerify getTradeVerifyBiId(Integer verifyId)throws BusinessException {
        return verifyMapper.getTradeVerifyBiId(verifyId);
    }

    @LcnTransaction
    @Transactional
    @Override
    public List<TradeVerify> getTradeVerifyList(TradeVerify record)throws BusinessException {
        return verifyMapper.getTradeVerifyList(record);
    }
}
