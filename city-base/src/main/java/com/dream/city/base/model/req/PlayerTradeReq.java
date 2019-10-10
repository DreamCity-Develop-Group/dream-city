package com.dream.city.base.model.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class PlayerTradeReq implements Serializable {

    private Integer tradeId;

    private Integer accId;

    private String playerId;
    private String playerName;
    private String playerNick;

    private Integer orderId;
    private String orderName;
    private String orderNum;

    private String tradeType;
    private String tradeStatus;
    private String tradeDesc;

    private String createTimeStart;
    private String createTimeEnd;

    private Integer detailId;
    private String tradeDetailType;
    private String tradeDetailDesc;

    /**  审核人 */
    private String verifyUserName;
    private String verifyStatus;
    private String verifyDesc;

}