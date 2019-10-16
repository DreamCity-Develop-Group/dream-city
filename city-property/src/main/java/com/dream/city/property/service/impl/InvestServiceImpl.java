package com.dream.city.property.service.impl;


import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.model.mapper.CityInvestMapper;
import com.dream.city.base.model.req.CityInvestReq;
import com.dream.city.base.model.resp.InvestResp;
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
    public CityInvest getInvestByIdOrName(CityInvest record) {
        if (record.getInId() == null){
            return null;
        }
        return investMapper.selectByPrimaryKey(record.getInId());
    }

    @Override
    public int updateInvest(CityInvest record) {
        Integer integer = investMapper.updateByPrimaryKeySelective(record);
        return integer ==null?0:integer;
    }

    @Override
    public List<InvestResp> getInvestLsit(CityInvestReq record) {
        return investMapper.getInvestLsit(record);
    }

}
