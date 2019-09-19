package com.dream.city.player.domain.entity;

import java.io.Serializable;

public class PlayerEarning implements Serializable {
    /**  */
    private Integer earnId;

    /** 用户id */
    private String earnPlayerId;

    /** 最大提取额度 */
    private Double earnMax;

    /** 税金 */
    private Double earnTax;

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
}