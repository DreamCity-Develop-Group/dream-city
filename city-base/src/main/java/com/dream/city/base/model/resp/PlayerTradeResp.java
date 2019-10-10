package com.dream.city.base.model.resp;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PlayerTradeResp implements Serializable {

    private Integer tradeId;

    private Integer tradeAccId;

    private String playerId;
    private String playerName;
    private String playerNick;

    private Integer tradeOrderId;

    private BigDecimal tradeAmount;
    private BigDecimal personalTax;
    private BigDecimal enterpriseTax;

    private String verifyStatus;

    private String inOut;

    private String tradeType;

    private String tradeDesc;

    private String createTime;

    private String updateTime;



}