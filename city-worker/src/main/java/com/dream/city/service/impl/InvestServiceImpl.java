package com.dream.city.service.impl;

import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.domain.mapper.InvestMapper;
import com.dream.city.service.InvestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author WVv
 */
public class InvestServiceImpl implements InvestService {
    @Autowired
    InvestMapper investMapper;

    @Override
    public List<CityInvest> getAllCityInvests() {

        return investMapper.getAllCityInvests();
    }

    @Override
    public Date getEndTimeAt(Integer orderInvestId) {
        CityInvest invest = investMapper.getCityInvest(orderInvestId);

        return invest.getInEnd();
    }

    @Override
    public List<CityInvest> getInvests() {
        return investMapper.getAllCityInvests();
    }
}
