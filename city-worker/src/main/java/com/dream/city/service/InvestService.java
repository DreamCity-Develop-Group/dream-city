package com.dream.city.service;

import com.dream.city.base.model.entity.CityInvest;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface InvestService {
    List<CityInvest> getAllCityInvests();

    CityInvest getCityInvest(Integer investId);

    Date getEndTimeAt(Integer orderInvestId);

    List<CityInvest> getInvests();

    Map<String,String> getProfitCalculateTime(Date date);

    CityInvest getInvestById(Integer orderInvestId);
}
