package com.dream.city.base.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 玩家提现规则
 */
public class PlayerEarning implements Serializable {
    /**  */
    private Integer earnId;

    /** 用户id */
    private String earnPlayerId;

    /** 最大提取额度 */
    private BigDecimal earnMax;

    /** 税金 */
    private BigDecimal earnTax;

    private String isWithdrew;

    private Date createTime;
    private Date updateTime;

    public Integer getEarnId() {
        return earnId;
    }

    public void setEarnId(Integer earnId) {
        this.earnId = earnId;
    }

    public String getEarnPlayerId() {
        return earnPlayerId;
    }

    public void setEarnPlayerId(String earnPlayerId) {
        this.earnPlayerId = earnPlayerId == null ? null : earnPlayerId.trim();
    }

    public BigDecimal getEarnMax() {
        return earnMax;
    }

    public void setEarnMax(BigDecimal earnMax) {
        this.earnMax = earnMax;
    }

    public BigDecimal getEarnTax() {
        return earnTax;
    }

    public void setEarnTax(BigDecimal earnTax) {
        this.earnTax = earnTax;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIsWithdrew() {
        return isWithdrew;
    }

    public void setIsWithdrew(String isWithdrew) {
        this.isWithdrew = isWithdrew;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}