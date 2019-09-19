package com.dream.city.player.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class InvestOrder implements Serializable {
    /**  */
    private Integer orderId;

    /** 投资项目ID */
    private Integer orderInvestId;

    /** 玩家ID */
    private String orderPayerId;

    /** 状态 */
    private Byte orderState;

    /** 是否复投 */
    private Byte orderRepeat;

    /**  */
    private Date createTime;

    /**  */
    private Date updateTime;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderInvestId() {
        return orderInvestId;
    }

    public void setOrderInvestId(Integer orderInvestId) {
        this.orderInvestId = orderInvestId;
    }

    public String getOrderPayerId() {
        return orderPayerId;
    }

    public void setOrderPayerId(String orderPayerId) {
        this.orderPayerId = orderPayerId == null ? null : orderPayerId.trim();
    }

    public Byte getOrderState() {
        return orderState;
    }

    public void setOrderState(Byte orderState) {
        this.orderState = orderState;
    }

    public Byte getOrderRepeat() {
        return orderRepeat;
    }

    public void setOrderRepeat(Byte orderRepeat) {
        this.orderRepeat = orderRepeat;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}