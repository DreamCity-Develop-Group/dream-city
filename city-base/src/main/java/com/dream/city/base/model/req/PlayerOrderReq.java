package com.dream.city.base.model.req;

import java.io.Serializable;
import java.util.Date;

/**
 * 玩家订单
 */
public class PlayerOrderReq  implements Serializable {

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

    @Override
    public String toString() {
        return "PlayerOrderReq{" +
                "orderId=" + orderId +
                ", investId=" + investId +
                ", inName='" + inName + '\'' +
                ", payerId='" + payerId + '\'' +
                ", orderState=" + orderState +
                ", orderRepeat=" + orderRepeat +
                ", orderStartTime=" + orderStartTime +
                ", orderEndTime=" + orderEndTime +
                '}';
    }
}
