package com.dream.city.service;

import com.dream.city.base.model.entity.InvestOrder;
import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.base.model.enu.InvestStatus;

import java.util.List;
import java.util.Map;

/**
 * @author Wvv
 *
 *
 */
public interface InvestOrderService {

    /**
     * 获取订单规则Map
     *
     * @param investId
     * @param rules
     * @return
     */
    Map<String, List<InvestOrder>> getInvestOrdersByCurrentDay(Integer investId, List<InvestRule> rules);

    /**
     *指设置订单
     *
     * @param orders
     * @param status
     */
    void setOrderState(List<InvestOrder> orders, InvestStatus status);

    /**
     * 设置订单状态
     * @param order
     * @param status
     */
    void updateOrderState(InvestOrder order,InvestStatus status);
}
