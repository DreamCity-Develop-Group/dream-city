package com.dream.city.base.model.req;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wvv
 */
@Data
public class AccountUpdateReq implements Serializable {
    /**  */
    private String accId;

    private String playerId;

    /**
     * 玩家账户地址
     */
    private String accAddr;

    /** 账户usdt额度 */
    private String accUsdt;

    /** usdt可用金额 */
    private String accUsdtAvailable;

    /** usdt冻结金额 */
    private String accUsdtFreeze;

    /** 账户mt额度 */
    private String accMt;

    /** mt可用金额 */
    private String accMtAvailable;

    /** mt冻结金额 */
    private String accMtFreeze;



}