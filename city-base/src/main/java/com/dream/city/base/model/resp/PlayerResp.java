package com.dream.city.base.model.resp;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PlayerResp implements Serializable {

    private Long id;

    private String playerId;

    private String friendId;

    private String playerName;

    private String playerNick;

    private String playerPass;

    private String playerTradePass;

    private String playerInvite;

    private Integer playerLevel;

    private String isValid;

    //玩家等级
    private String grade;

    //玩家积分
    private int integral;

    //商会成员数
    private int commerceMember;

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

    public Integer getPlayerLevel() {
        return playerLevel;
    }

    public void setPlayerLevel(Integer playerLevel) {
        this.playerLevel = playerLevel;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public int getCommerceMember() {
        return commerceMember;
    }

    public void setCommerceMember(int commerceMember) {
        this.commerceMember = commerceMember;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }
}