package com.dream.city.base.model.entity;

import java.io.Serializable;
import java.util.Date;

public class PlayerEarning implements Serializable {
    /**  */
    private Integer earnId;

    /** 用户id */
    private String earnPlayerId;

    /** 最大提取额度 */
    private Double earnMax;

    /** 税金 */
    private Double earnTax;

    private Date createTime;

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

    public Double getEarnMax() {
        return earnMax;
    }

    public void setEarnMax(Double earnMax) {
        this.earnMax = earnMax;
    }

    public Double getEarnTax() {
        return earnTax;
    }

    public void setEarnTax(Double earnTax) {
        this.earnTax = earnTax;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}