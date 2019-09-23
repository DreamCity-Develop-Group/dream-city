package com.dream.city.base.model.req;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wvv
 */
public class PlayerAccountReq implements Serializable {

    /** 账号id */
    private Integer accId;

    /** 账户玩家 */
    private String accPlayerId;
    private String username;
    private String nick;

    /**
     * 玩家账户地址
     */
    private String accAddr;

    /**
     * 玩家账户密码
     */
    private String accPass;

    /**
     * 交易类型（投资usdt，投资mt，转账transfer，充值recharge，提现withdraw）
     */
    private String tradeType;

    /**
     * 资金动态(进账in,出账out)
     */
    private String amountDynType;

    /** 账户usdt额度 */
    private BigDecimal accUsdt;

    /** 账户mt额度 */
    private BigDecimal accMt;

    /** usdt可用金额 */
    private BigDecimal accUsdtAvailable;

    /** usdt冻结金额 */
    private BigDecimal accUsdtFreeze;

    /** mt可用金额 */
    private BigDecimal accMtAvailable;

    /** mt冻结金额 */
    private BigDecimal accMtFreeze;

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
        this.accPlayerId = accPlayerId;
    }

    public String getAccAddr() {
        return accAddr;
    }

    public void setAccAddr(String accAddr) {
        this.accAddr = accAddr;
    }

    public String getAccPass() {
        return accPass;
    }

    public void setAccPass(String accPass) {
        this.accPass = accPass;
    }

    public BigDecimal getAccUsdt() {
        return accUsdt;
    }

    public void setAccUsdt(BigDecimal accUsdt) {
        this.accUsdt = accUsdt;
    }

    public BigDecimal getAccMt() {
        return accMt;
    }

    public void setAccMt(BigDecimal accMt) {
        this.accMt = accMt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getAmountDynType() {
        return amountDynType;
    }

    public void setAmountDynType(String amountDynType) {
        this.amountDynType = amountDynType;
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
}