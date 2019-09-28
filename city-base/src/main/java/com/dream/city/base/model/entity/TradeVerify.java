package com.dream.city.base.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TradeVerify  implements Serializable {
    private Integer verifyId;

    /** 交易id(交易记录表) */
    private Integer verifyTradeId;
    private Integer verifyOrderId;

    /**  审核人id(员工表) */
    private Integer verifyEmpId;

    private BigDecimal verifyAmount;

    /** 审核状态(待审核wait，审核中verifying，pass审核通过，notpass审核不通过)*/
    private String verifyStatus;

    /** 审核意见 */
    private String verifyDesc;

    private Date createTime;

    private Date updateTime;

    public Integer getVerifyId() {
        return verifyId;
    }

    public void setVerifyId(Integer verifyId) {
        this.verifyId = verifyId;
    }

    public Integer getVerifyTradeId() {
        return verifyTradeId;
    }

    public void setVerifyTradeId(Integer verifyTradeId) {
        this.verifyTradeId = verifyTradeId;
    }

    public Integer getVerifyEmpId() {
        return verifyEmpId;
    }

    public void setVerifyEmpId(Integer verifyEmpId) {
        this.verifyEmpId = verifyEmpId;
    }

    public BigDecimal getVerifyAmount() {
        return verifyAmount;
    }

    public void setVerifyAmount(BigDecimal verifyAmount) {
        this.verifyAmount = verifyAmount;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus == null ? null : verifyStatus.trim();
    }

    public String getVerifyDesc() {
        return verifyDesc;
    }

    public void setVerifyDesc(String verifyDesc) {
        this.verifyDesc = verifyDesc == null ? null : verifyDesc.trim();
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

    public Integer getVerifyOrderId() {
        return verifyOrderId;
    }

    public void setVerifyOrderId(Integer verifyOrderId) {
        this.verifyOrderId = verifyOrderId;
    }
}