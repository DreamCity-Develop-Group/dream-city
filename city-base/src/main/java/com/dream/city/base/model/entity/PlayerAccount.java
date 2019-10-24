package com.dream.city.base.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wvv
 */
@Data
public class PlayerAccount implements Serializable {
    /**  */
    private Integer accId;

    /** 账户玩家 */
    private String accPlayerId;
    /**
     * 玩家账户地址
     */
    private String accAddr;

    /**
     * 玩家账户密码
     */
    private String accPass;

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

    /** 积累总收入 */
    private BigDecimal accIncome;

    //private Integer version;

    /**  */
    private Date createTime;

    /**  */
    private Date updateTime;
}