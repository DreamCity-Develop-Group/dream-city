package com.dream.city.service;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.SalesOrder;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.List;

public interface SalesOrderService {

    List<SalesOrder> selectSalesOrder(String playerId);

    SalesOrder getSalesOrder(Long id);

    SalesOrder getSalesOrder(String orderId);

    List<SalesOrder> selectSalesSellerOrder(String playerId);

    List<SalesOrder> selectSalesBuyerOrder(String playerId);

    SalesOrder getBuyerNoPayOrder(String playerId);

    Result buyMtCreate(BigDecimal buyAmount, BigDecimal rate, String playerId);

    Result buyMtFinish(String playerId, String pass);

    BigDecimal getUsdtToMtRate();

    Result sendOrderMt(List<SalesOrder>orders);
}
