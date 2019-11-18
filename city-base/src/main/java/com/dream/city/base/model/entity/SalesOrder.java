package com.dream.city.base.model.entity;

import com.dream.city.base.model.enu.OrderState;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Wvv
 */
@Data
public class SalesOrder {
    private Integer id;
    private String orderId;
    private BigDecimal orderAmount;
    private String orderBuyType;
    private String orderPayType;
    private BigDecimal orderPayAmount;
    private String orderPlayerBuyer;
    private String orderPlayerSeller;
    private int orderState;
    private Date createTime;
    private Date updateTime;
    public SalesOrder(){super();}
    public SalesOrder(String orderId,BigDecimal orderAmount, String orderBuyType, String orderPayType, BigDecimal orderPayAmount, String orderPlayerBuyer, String orderPlayerSeller, int orderState) {
        this.orderId = orderId;
        this.orderAmount = orderAmount;
        this.orderBuyType = orderBuyType;
        this.orderPayType = orderPayType;
        this.orderPayAmount = orderPayAmount;
        this.orderPlayerBuyer = orderPlayerBuyer;
        this.orderPlayerSeller = orderPlayerSeller;
        this.orderState = orderState;
        this.createTime = new Date();
    }
}
