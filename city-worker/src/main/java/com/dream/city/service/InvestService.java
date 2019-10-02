package com.dream.city.service;

import com.dream.city.base.model.entity.CityInvest;

import java.util.Date;
import java.util.List;

public interface InvestService {
    List<CityInvest> getAllCityInvests();

    Date getEndTimeAt(Integer orderInvestId);

    List<CityInvest> getInvests();
}
