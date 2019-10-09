package com.dream.city.base.model.req;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PlayerTradeReq implements Serializable {

    private Integer tradeId;

    private Integer accId;

    private String playerId;
    private String playerName;
    private String playerNick;

    private Integer orderId;

    private String tradeType;

    private String tradeAmountType;

    private String tradeDesc;

    private String createTimeStart;
    private String createTimeEnd;





}