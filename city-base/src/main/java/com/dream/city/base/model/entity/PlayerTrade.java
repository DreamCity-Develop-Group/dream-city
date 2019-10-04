package com.dream.city.base.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PlayerTrade implements Serializable {

    private Integer tradeId;

    private Integer tradeAccId;

    private String tradePlayerId;

    private Integer tradeOrderId;

    private BigDecimal tradeAmount;

    private String tradeType;

    private String tradeAmountType;

    private String tradeDesc;

    private Date createTime;

    private Date updateTime;

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public Integer getTradeAccId() {
        return tradeAccId;
    }

    public void setTradeAccId(Integer tradeAccId) {
        this.tradeAccId = tradeAccId;
    }

    public String getTradePlayerId() {
        return tradePlayerId;
    }

    public void setTradePlayerId(String tradePlayerId) {
        this.tradePlayerId = tradePlayerId == null ? null : tradePlayerId.trim();
    }

    public Integer getTradeOrderId() {
        return tradeOrderId;
    }

    public void setTradeOrderId(Integer tradeOrderId) {
        this.tradeOrderId = tradeOrderId;
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    public String getTradeAmountType() {
        return tradeAmountType;
    }

    public void setTradeAmountType(String tradeAmountType) {
        this.tradeAmountType = tradeAmountType == null ? null : tradeAmountType.trim();
    }

    public String getTradeDesc() {
        return tradeDesc;
    }

    public void setTradeDesc(String tradeDesc) {
        this.tradeDesc = tradeDesc == null ? null : tradeDesc.trim();
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