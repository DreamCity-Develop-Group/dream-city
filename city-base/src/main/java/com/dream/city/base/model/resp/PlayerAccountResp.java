package com.dream.city.base.model.resp;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wvv
 */
@ToString
@Data
public class PlayerAccountResp implements Serializable {
    /**  */
    private Integer accId;

    /** 账户玩家 */
    private String playerId;

    private String playerName;

    /** 玩家等级 */
    private String grade;

    /** 商会成员数 */
     private String commerceMember;

    /** 商会邀请码*/
    private String invite;

    /** 总资产 */
    private BigDecimal totalProperty;

    /** 总收入*/
    private BigDecimal totalIncome;

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

    public Integer getAccId() {
        return accId;
    }

    public void setAccId(Integer accId) {
        this.accId = accId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
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

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCommerceMember() {
        return commerceMember;
    }

    public void setCommerceMember(String commerceMember) {
        this.commerceMember = commerceMember;
    }

    public String getInvite() {
        return invite;
    }

    public void setInvite(String invite) {
        this.invite = invite;
    }

    public BigDecimal getTotalProperty() {
        return totalProperty;
    }

    public void setTotalProperty(BigDecimal totalProperty) {
        this.totalProperty = totalProperty;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}