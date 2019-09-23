package com.dream.city.base.model.resp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PlayerTradeResp implements Serializable {

    private Integer tradeId;

    private Integer tradeAccId;

    private String playerId;
    private String playerName;
    private String playerNick;

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

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerNick() {
        return playerNick;
    }

    public void setPlayerNick(String playerNick) {
        this.playerNick = playerNick;
    }
}