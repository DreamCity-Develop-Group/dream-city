package com.dream.city.invest.service;

import com.dream.city.base.model.entity.InvestOrder;
import com.dream.city.base.model.req.InvestOrderReq;
import com.dream.city.base.model.resp.InvestOrderResp;

import java.util.List;

/**
 * 投资订单
 */
public interface OrderService {



    /**
     * 预约投资
     * @param record
     * @return
     */
    InvestOrder insertInvestOrder(InvestOrder record);

    /**
     * 投资
     * 投资状态改成经营中
     * @param orderId
     * @return
     */
    int playerInvesting(Integer orderId);

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
     * @param orderId
     * @return
     */
    InvestOrderResp getInvestOrderById(Integer orderId);

    InvestOrder getOrderByPlayerIdInvestId(String playerId,Integer investId);

    /**
     * 投资订单列表
     * @param record
     * @return
     */
    List<InvestOrderResp> getInvestOrderList(InvestOrderReq record);

    /**
     * 投资数量
     * @param record orderInvestId、orderPayerId、orderState
     * @return
     */
    int countOrdersByPayerIdInvestId(InvestOrder record);
}
