package com.dream.city.invest.service;

import com.dream.city.base.model.entity.TradeVerify;

import java.util.List;

/**
 * 交易审核
 */
public interface TradeVerifyService {

    int insertTradeVerify(TradeVerify record);

    int updateTradeVerify(TradeVerify record);

    TradeVerify getTradeVerify(Integer verifyId);

    List<TradeVerify> getTradeVerifyList(TradeVerify record);

}
