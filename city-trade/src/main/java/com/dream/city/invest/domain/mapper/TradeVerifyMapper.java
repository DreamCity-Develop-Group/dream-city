package com.dream.city.invest.domain.mapper;


import com.dream.city.base.model.entity.TradeVerify;

import java.util.List;

public interface TradeVerifyMapper {

    int insertSelective(TradeVerify record);

    TradeVerify getTradeVerifyBiId(Integer verifyId);

    List<TradeVerify> getTradeVerifyList(TradeVerify record);

    int updateByPrimaryKeySelective(TradeVerify record);

}