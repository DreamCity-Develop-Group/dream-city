package com.dream.city.invest.service;

import com.dream.city.base.model.entity.TradeVerify;

import java.util.List;

/**
 * 交易审核
 */
public interface TradeVerifyService {

    /**
     * 新增审核
     * @param record
     * @return
     */
    int insertTradeVerify(TradeVerify record);

    /**
     * 审核
     * 提现、转账、投资
     * @param record
     * @return
     */
    int updateTradeVerify(TradeVerify record);

    /**
     * 根据id获取审核
     * @param verifyId
     * @return
     */
    TradeVerify getTradeVerifyBiId(Integer verifyId);

    /**
     * 审核列表
     * @param record
     * @return
     */
    List<TradeVerify> getTradeVerifyList(TradeVerify record);

}
