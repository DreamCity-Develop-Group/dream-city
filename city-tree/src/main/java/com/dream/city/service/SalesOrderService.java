package com.dream.city.service;

import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.SalesOrder;
import com.dream.city.base.model.entity.SalesRefuseOrder;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.List;

public interface SalesOrderService {

    List<SalesOrder> selectSalesOrder(String playerId,Integer start,Integer size);

    List<SalesOrder> selectSalesOrderUnSend(String playerId);

    List<SalesOrder> selectSalesOrderOvertime(String playerId);

    SalesOrder getSalesOrder(Long id);

    SalesOrder getSalesOrder(String orderId);

    List<SalesOrder> selectSalesSellerOrder(String playerId);

    List<SalesOrder> selectSalesBuyerOrder(String playerId);

    SalesOrder getBuyerNoPayOrder(String playerId);

    Result buyMtCreate(BigDecimal buyAmount, BigDecimal rate, String playerId);

    Result buyMtFinish(String playerId, String pass);

    BigDecimal getUsdtToMtRate(String playerId);

    Result sendOrderMt(List<SalesOrder>orders);

    void updateOrder(SalesOrder salesOrder);

    void addOrder(SalesOrder salesOrder);

    List<SalesOrder> selectSalesBuyerRefuseOrders(String buyer, String seller);

    int selectSalesSellerRejectTimes(String buyer_id, String sellerId, int status);

    void addTradeDetail(SalesOrder order, Integer tradeId, PlayerAccount account, String status, String desc);
    void addPlayerTrade(SalesOrder order, PlayerAccount account,String tradeType,String tradeStatus, String outStatus, String desc);

    void addRefuseOrder(SalesRefuseOrder refuseOrder);

    SalesRefuseOrder getRefuseOrder(String playerId, String orderId);
}
