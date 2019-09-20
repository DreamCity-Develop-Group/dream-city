package com.dream.city.property.service.impl;


import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.property.domain.mapper.CityInvestMapper;
import com.dream.city.property.service.InvestService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestServiceImpl implements InvestService {

    @Autowired
    private CityInvestMapper investMapper;



    @Override
    public int insertInvest(CityInvest record) {
        Integer integer = investMapper.insertSelective(record);
        return integer ==null?0:integer;
    }

    @Override
    public CityInvest getInvest(CityInvest record) {
        if (record.getInId() == null && StringUtils.isBlank(record.getInName())){
            return null;
        }
        return investMapper.selectByPrimaryKey(record);
    }

    @Override
    public int updateInvest(CityInvest record) {
        Integer integer = investMapper.updateByPrimaryKeySelective(record);
        return integer ==null?0:integer;
    }

    @Override
    public List<CityInvest> getInvestLsit(CityInvest record) {
        return investMapper.getInvestLsit(record);
    }

}
