package com.dream.city.base.model.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class CityInvestReq implements Serializable {

    private Integer inId;
    private Integer orderId;
    /** 名称 */
    private String inName;
    private String isValid;
    private Integer inType;

    /** 玩家ID */
    private String playerId;
    private String playerName;
    private String playerNick;


}