package com.dream.city.base.model.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class EarningReq implements Serializable {

    private Integer earnId;
    private Integer inId;
    /** 名称 */
    private String inName;
    private Integer inType;
    private Integer orderId;

    /** 玩家ID */
    private String playerId;
    private String playerName;
    private String playerNick;


}