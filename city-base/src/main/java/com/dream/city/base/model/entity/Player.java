package com.dream.city.base.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Player implements Serializable {
    private Long id;

    private String playerId;

    private String playerName;

    private String playerNick;

    private String playerPass;

    private String playerTradePass;

    private String playerInvite;

    private Integer playerLevel;

    private String isValid;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId == null ? null : playerId.trim();
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

    public String getPlayerPass() {
        return playerPass;
    }

    public void setPlayerPass(String playerPass) {
        this.playerPass = playerPass;
    }

    public String getPlayerTradePass() {
        return playerTradePass;
    }

    public void setPlayerTradePass(String playerTradePass) {
        this.playerTradePass = playerTradePass;
    }

    public String getPlayerInvite() {
        return playerInvite;
    }

    public void setPlayerInvite(String playerInvite) {
        this.playerInvite = playerInvite;
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

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }
}