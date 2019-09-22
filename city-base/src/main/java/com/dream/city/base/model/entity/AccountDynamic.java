package com.dream.city.base.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 交易流水
 */
public class AccountDynamic implements Serializable {

    /**  */
    private Integer dynId;

    /** 账户id */
    private Integer dynAccId;

    /** 订单id */
    private Integer orderId;

    private String dynPlayerId;

    /** 交易金额 */
    private BigDecimal dynAmount;

    /** 交易类型(收入in,支出out) */
    private String dynType;

    /** 资金类型（投资usdt，投资mt，转账transfer，充值recharge，提现withdraw） */
    private String dynAmountType;

    /** 交易描述 */
    private String dynDesc;

    /** 交易时间 */
    private Date createTime;

    public Integer getDynId() {
        return dynId;
    }

    public void setDynId(Integer dynId) {
        this.dynId = dynId;
    }

    public Integer getDynAccId() {
        return dynAccId;
    }

    public void setDynAccId(Integer dynAccId) {
        this.dynAccId = dynAccId;
    }

    public BigDecimal getDynAmount() {
        return dynAmount;
    }

    public void setDynAmount(BigDecimal dynAmount) {
        this.dynAmount = dynAmount;
    }

    public String getDynType() {
        return dynType;
    }

    public void setDynType(String dynType) {
        this.dynType = dynType == null ? null : dynType.trim();
    }

    public String getDynAmountType() {
        return dynAmountType;
    }

    public void setDynAmountType(String dynAmountType) {
        this.dynAmountType = dynAmountType == null ? null : dynAmountType.trim();
    }

    public String getDynDesc() {
        return dynDesc;
    }

    public void setDynDesc(String dynDesc) {
        this.dynDesc = dynDesc == null ? null : dynDesc.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getDynPlayerId() {
        return dynPlayerId;
    }

    public void setDynPlayerId(String dynPlayerId) {
        this.dynPlayerId = dynPlayerId;
    }
}