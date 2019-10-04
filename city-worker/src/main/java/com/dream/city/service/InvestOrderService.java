package com.dream.city.service;

import com.dream.city.base.model.entity.InvestOrder;
import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.base.model.enu.InvestStatus;

import java.math.BigDecimal;
import java.util.Date;
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
    Map<String, List<InvestOrder>> getInvestOrdersByCurrentDay(Integer investId, List<InvestRule> rules,int[] states);

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

    /**
     * 找出所有成功的订单，计算所得到的资金总额度
     *
     * @param inId
     * @return
     */
    List<InvestOrder> getInvestOrdersAmountByDayInterval(Integer inId, String start, String end);

    List<InvestOrder> getInvestOrdersByCurrent(Integer inId, int state,String start,String end);

    /**
     * 查出符合条件的记录总数
     *
     * @param investId
     * @param states
     * @return
     */
    int getInvestOrdersSum(Integer investId, int[] states);

    /**
     * 第一次投资的订单
     *
     * @param inId
     * @return
     */
    List<InvestOrder> getInvestOrdersFirstTime(Integer inId);
}
