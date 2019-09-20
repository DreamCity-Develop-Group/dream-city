package com.dream.city.base.model.req;

import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 玩家投资订单
 */
@ToString
public class InvestOrderReq implements Serializable {

    /**
     * 订单id
     */
    private Integer orderId;

    /** 项目ID */
    private Integer investId;

    /** 名称 */
    private String inName;

    /** 玩家ID */
    private String payerId;

    /** 订单状态 */
    private int orderState;

    /** 订单是否复投 */
    private int orderRepeat;

    /** 限额 */
    private Float inLimit;

    /** 开始时间 */
    private Date inStart;

    /** 税金 */
    private Double inTax;

    /** 收益倍数 */
    private String inEarning;

    /** 投资结束时间 */
    private Date inEnd;

    /**  */
    private Date orderStartTime;

    /**  */
    private Date orderEndTime;


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getInvestId() {
        return investId;
    }

    public void setInvestId(Integer investId) {
        this.investId = investId;
    }

    public String getInName() {
        return inName;
    }

    public void setInName(String inName) {
        this.inName = inName;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public int getOrderRepeat() {
        return orderRepeat;
    }

    public void setOrderRepeat(int orderRepeat) {
        this.orderRepeat = orderRepeat;
    }

    public Date getOrderStartTime() {
        return orderStartTime;
    }

    public void setOrderStartTime(Date orderStartTime) {
        this.orderStartTime = orderStartTime;
    }

    public Date getOrderEndTime() {
        return orderEndTime;
    }

    public void setOrderEndTime(Date orderEndTime) {
        this.orderEndTime = orderEndTime;
    }

    public Float getInLimit() {
        return inLimit;
    }

    public void setInLimit(Float inLimit) {
        this.inLimit = inLimit;
    }

    public Date getInStart() {
        return inStart;
    }

    public void setInStart(Date inStart) {
        this.inStart = inStart;
    }

    public Double getInTax() {
        return inTax;
    }

    public void setInTax(Double inTax) {
        this.inTax = inTax;
    }

    public String getInEarning() {
        return inEarning;
    }

    public void setInEarning(String inEarning) {
        this.inEarning = inEarning;
    }

    public Date getInEnd() {
        return inEnd;
    }

    public void setInEnd(Date inEnd) {
        this.inEnd = inEnd;
    }


}
