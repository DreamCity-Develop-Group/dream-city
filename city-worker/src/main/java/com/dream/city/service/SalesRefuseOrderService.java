package com.dream.city.service;

import com.dream.city.base.model.entity.SalesOrder;

import java.util.List;

/**
 * @author Wvv
 *
 */
public interface SalesRefuseOrderService {

    Integer selectSalesBuyerRefuseOrders(String buyId,String sellId);
}
