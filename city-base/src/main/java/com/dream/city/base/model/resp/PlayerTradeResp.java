package com.dream.city.base.model.resp;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class PlayerTradeResp implements Serializable {

    private Integer tradeId;
    private Integer tradeAccId;
    private BigDecimal tradeAmount;
    private BigDecimal usdtSurplus;
    private BigDecimal mtSurplus;
    private BigDecimal personalTax;
    private BigDecimal enterpriseTax;
    private BigDecimal quotaTax;
    private String tradeStatus;
    private String inOutStatus;
    private String tradeType;
    private String tradeDesc;
    private String createTime;
    private String updateTime;

    private String playerId;
    private String playerName;
    private String playerNick;

    private Integer orderId;
    private String orderName;
    private String orderNum;

    private Integer detailId;
    private String tradeDetailType;
    private String detailDesc;
    private String detailTime;

    private Integer verifyId;
    private Integer verifyEmpId;
    /**  审核人 */
    private String verifyUserName;
    private String verifyStatus;
    private String verifyToAddress;
    private String verifyDesc;
    private String verifyTime;


    /** 预计最大收益，达到就可以出局 */
    private BigDecimal earnMax;
    /**
     * 当前获利
     */
    private BigDecimal earnCurrent;
    /**
     * 是否已经可以提取
     */
    private String isWithdrew;


}