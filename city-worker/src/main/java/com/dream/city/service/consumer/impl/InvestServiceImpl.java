package com.dream.city.service.consumer.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.model.mapper.InvestMapper;
import com.dream.city.service.InvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author WVv
 *
 *
 */
@Service
public class InvestServiceImpl implements InvestService {

    @Autowired
    InvestMapper investMapper;

    @LcnTransaction
    @Transactional
    @Override
    public List<CityInvest> getAllCityInvests() {

        return investMapper.getAllCityInvests();
    }

    @LcnTransaction
    @Transactional
    @Override
    public CityInvest getCityInvest(Integer investId)throws BusinessException {
        return investMapper.getCityInvest(investId);
    }

    @LcnTransaction
    @Transactional
    @Override
    public Date getEndTimeAt(Integer orderInvestId)throws BusinessException {
        CityInvest invest = investMapper.getCityInvest(orderInvestId);

        return invest.getInEnd();
    }

    @LcnTransaction
    @Transactional
    @Override
    public List<CityInvest> getInvests()throws BusinessException {
        return investMapper.getAllCityInvests();
    }

    @LcnTransaction
    @Transactional
    @Override
    public Map<String, String> getProfitCalculateTime(Date date)throws BusinessException {
        Map<String,String> times = new HashMap<>();
        Calendar cal = Calendar.getInstance();
        //时分格式
        SimpleDateFormat df1 = new SimpleDateFormat("HH:mm");
        //年月日格式
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = df1.format(date);

        //昨天
        cal.add(Calendar.DATE, -1);
        String yesterday = dt.format(cal.getTime());
        String end = yesterday + " " + date1;
        //前天
        cal.add(Calendar.DATE, -2);
        String yesterdayMore = dt.format(cal.getTime());
        String start = yesterdayMore + " " + date1;
        times.put("start",start);
        times.put("end",end);
        return times;
    }

    @LcnTransaction
    @Transactional
    @Override
    public CityInvest getInvestById(Integer orderInvestId)throws BusinessException {
        return investMapper.getCityInvest(orderInvestId);
    }
}
