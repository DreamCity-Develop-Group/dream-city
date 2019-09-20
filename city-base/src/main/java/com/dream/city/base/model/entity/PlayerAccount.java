package com.dream.city.base.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wvv
 */
public class PlayerAccount implements Serializable {
    /**  */
    private Integer accId;

    /** 账户玩家 */
    private String accPlayerId;

    /** 账户usdt额度 */
    private BigDecimal accUsdt;

    /** usdt可用金额 */
    private BigDecimal accUsdtAvailable;

    /** usdt冻结金额 */
    private BigDecimal accUsdtFreeze;

    /** 账户mt额度 */
    private BigDecimal accMt;

    /** mt可用金额 */
    private BigDecimal accMtAvailable;

    /** mt冻结金额 */
    private BigDecimal accMtFreeze;

    /**  */
    private Date createTime;

    /**  */
    private Date updateTime;

    public Integer getAccId() {
        return accId;
    }

    public void setAccId(Integer accId) {
        this.accId = accId;
    }

    public String getAccPlayerId() {
        return accPlayerId;
    }

    public void setAccPlayerId(String accPlayerId) {
        this.accPlayerId = accPlayerId == null ? null : accPlayerId.trim();
    }

    public BigDecimal getAccUsdt() {
        return accUsdt;
    }

    public void setAccUsdt(BigDecimal accUsdt) {
        this.accUsdt = accUsdt;
    }

    public BigDecimal getAccUsdtAvailable() {
        return accUsdtAvailable;
    }

    public void setAccUsdtAvailable(BigDecimal accUsdtAvailable) {
        this.accUsdtAvailable = accUsdtAvailable;
    }

    public BigDecimal getAccUsdtFreeze() {
        return accUsdtFreeze;
    }

    public void setAccUsdtFreeze(BigDecimal accUsdtFreeze) {
        this.accUsdtFreeze = accUsdtFreeze;
    }

    public BigDecimal getAccMt() {
        return accMt;
    }

    public void setAccMt(BigDecimal accMt) {
        this.accMt = accMt;
    }

    public BigDecimal getAccMtAvailable() {
        return accMtAvailable;
    }

    public void setAccMtAvailable(BigDecimal accMtAvailable) {
        this.accMtAvailable = accMtAvailable;
    }

    public BigDecimal getAccMtFreeze() {
        return accMtFreeze;
    }

    public void setAccMtFreeze(BigDecimal accMtFreeze) {
        this.accMtFreeze = accMtFreeze;
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