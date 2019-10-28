package com.dream.city.property.service.impl;


import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.model.mapper.CityInvestMapper;
import com.dream.city.base.model.req.CityInvestReq;
import com.dream.city.base.model.resp.InvestResp;
import com.dream.city.property.service.InvestService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InvestServiceImpl implements InvestService {

    @Autowired
    private CityInvestMapper investMapper;


    @LcnTransaction
    @Transactional
    @Override
    public int insertInvest(CityInvest record) throws BusinessException {
        Integer integer = investMapper.insertSelective(record);
        return integer ==null?0:integer;
    }

    @LcnTransaction
    @Transactional
    @Override
    public InvestResp getInvest(CityInvestReq record) throws BusinessException {
        return investMapper.selectCityInvest(record);
    }

    @LcnTransaction
    @Transactional
    @Override
    public int updateInvest(CityInvest record)  throws BusinessException{
        Integer integer = investMapper.updateByPrimaryKeySelective(record);
        return integer ==null?0:integer;
    }

    @LcnTransaction
    @Transactional
    @Override
    public List<InvestResp> getInvestLsit(CityInvestReq record) throws BusinessException {
        return investMapper.getInvestLsit(record);
    }

}
