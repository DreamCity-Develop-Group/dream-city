package com.dream.city.service;

import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.base.model.entity.RuleItem;
import com.dream.city.base.model.entity.SalesOrder;

import java.util.List;

/**
 * @author Wvv
 *
 */
public interface SalesOrderService {

    List<SalesOrder> getSalesOrdersByState(int state);

    SalesOrder getSalesOrder();

    int selectSalesSellerRejectTimes(String buyer_id, String sellerId, int status);

    List<SalesOrder> getOverTimeOrder(String time);
}
