package com.dream.city.player.service;

import com.dream.city.base.model.entity.InvestOrder;

import java.util.List;

/**
 * 投资订单
 */
public interface InvestOrderService {



    /**
     * 投资物业
     * @param record
     * @return
     */
    int insertInvestOrder(InvestOrder record);

    /**
     * 投资订单作废
     * @param record
     * @return
     */
    int investOrderInvalid(InvestOrder record);

    /**
     * 投资订单取消
     * @param record
     * @return
     */
    int investOrderCancel(InvestOrder record);

    /**
     * 查询投资订单
     * @param record
     * @return
     */
    InvestOrder getInvestOrder(InvestOrder record);

    /**
     * 投资列表
     * @param record
     * @return
     */
    List<InvestOrder> getInvestOrders(InvestOrder record);



}
