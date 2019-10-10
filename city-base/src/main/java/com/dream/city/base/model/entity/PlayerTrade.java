package com.dream.city.base.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PlayerTrade implements Serializable {

    private Integer tradeId;

    private Integer tradeAccId;

    private String tradePlayerId;

    private Integer tradeOrderId;

    private BigDecimal tradeAmount;

    private BigDecimal personalTax;

    private BigDecimal enterpriseTax;

    private String tradeStatus;

    private String inOutStatus;

    private String tradeType;

    private String tradeDesc;

    private Date createTime;

    private Date updateTime;


}