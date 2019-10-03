package com.dream.city.invest.service.impl;

import com.dream.city.base.model.entity.TradeVerify;
import com.dream.city.base.model.mapper.TradeVerifyMapper;
import com.dream.city.invest.service.TradeVerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TradeVerifyServiceImpl implements TradeVerifyService {

    @Autowired
    TradeVerifyMapper verifyMapper;

    @Override
    public int insertTradeVerify(TradeVerify record) {
        Integer integer = verifyMapper.insertSelective(record);
        return integer == null? 0: integer;
    }

    @Override
    @Transactional
    public int updateTradeVerify(TradeVerify record) {
        Integer integer = verifyMapper.updateByPrimaryKeySelective(record);
        return integer == null? 0: integer;
    }

    @Override
    public TradeVerify getTradeVerifyBiId(Integer verifyId) {
        return verifyMapper.getTradeVerifyBiId(verifyId);
    }

    @Override
    public List<TradeVerify> getTradeVerifyList(TradeVerify record) {
        return verifyMapper.getTradeVerifyList(record);
    }
}
