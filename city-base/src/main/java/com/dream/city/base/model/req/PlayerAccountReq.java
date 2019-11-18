package com.dream.city.base.model.req;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author wvv
 */
@Data
public class PlayerAccountReq implements Serializable {

    /** 玩家账号id */
    private Integer accId;

    /** 平台账号id */
    private List<String> platformAccIds;

    /** 账户玩家 */
    private String accPlayerId;
    private String username;

    private String nick;
    /** 好友id */
    private String friendId;

    /**
     * 玩家账户地址
     */
    private String accAddr;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 玩家账户密码
     */
    private String accPass;
    /**
     * 交易密码
     */
    private String tradePass;

    /**
     * 交易类型（充值:recharge,转账:transfer,提现:withdraw,购买mt:buy_mt,投资invest)
     */
    private String tradeType;

    /** usdt，mt */
    private String amountType;
    /**
     * 订单id
     */
    private Integer tradeOrderId;

    /**
     * 资金动态(进账in,出账out)
     */
    private String inOut;

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

    private BigDecimal personalTax;

    private BigDecimal enterpriseTax;
    private BigDecimal inQuotaTax;

    private String tradeStatus;
    private Integer tradeId;
    /** 审核id */
    private Integer verifyId;
    private String tradeDetailType;
    /** 交易审核时间 */
    private Date verifyTime;

}