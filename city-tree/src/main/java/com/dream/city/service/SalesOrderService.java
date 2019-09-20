package com.dream.city.service;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.SalesOrder;

import java.math.BigDecimal;
import java.util.List;

public interface SalesOrderService {

    List<SalesOrder> selectSalesOrder(String playerId);

    SalesOrder getSalesOrder(Long id);

    List<SalesOrder> selectSalesSellerOrder(String playerId);

    List<SalesOrder> selectSalesBuyerOrder(String playerId);

    Result buyMt(BigDecimal buyAmount, String playerId);
}
