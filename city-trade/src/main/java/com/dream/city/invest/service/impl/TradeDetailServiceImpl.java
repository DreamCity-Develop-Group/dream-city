package com.dream.city.invest.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.TradeDetail;
import com.dream.city.base.model.mapper.TradeDetailMapper;
import com.dream.city.base.model.req.PlayerTradeReq;
import com.dream.city.base.model.resp.PlayerTradeResp;
import com.dream.city.invest.service.TradeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TradeDetailServiceImpl implements TradeDetailService {


    @Autowired
    TradeDetailMapper detailMapper;



    @LcnTransaction
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public TradeDetail insert(TradeDetail record) throws BusinessException {
        Integer integer = detailMapper.insertSelective(record);
        if (integer == null || integer < 1){
            return null;
        }
        return record;
    }

    @LcnTransaction
    @Transactional
    @Override
    public TradeDetail getById(Integer id)throws BusinessException {
        return detailMapper.selectByPrimaryKey(id);
    }

    @LcnTransaction
    @Transactional
    @Override
    public List<PlayerTradeResp> getList(PlayerTradeReq record)throws BusinessException {
        return detailMapper.getTradeDetailList(record);
    }
}
