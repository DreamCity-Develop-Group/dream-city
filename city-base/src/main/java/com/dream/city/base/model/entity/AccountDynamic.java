package com.dream.city.base.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AccountDynamic implements Serializable {
    /**  */
    private Integer dynId;

    /** 账户id */
    private Integer dynAccId;

    /** 订单id */
    private Integer orderId;

    /** 动账金额 */
    private BigDecimal dynAmount;

    /** 动账类型(收入in,支出out) */
    private String dynType;

    /** 资金类型（投资usdt，投资mt，转账transfer，提现withdraw） */
    private String dynAmountType;

    /** 动账描述 */
    private String dynDesc;

    /** 动账金额 */
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
}