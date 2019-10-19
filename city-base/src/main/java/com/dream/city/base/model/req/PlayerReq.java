package com.dream.city.base.model.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class PlayerReq implements Serializable {

    private Long id;

    private String playerId;

    private String playerName;

    private String playerNick;

    private String playerInvite;

    private Integer playerLevel;

    private String isValid;

    private String createTime;

    private Integer pageNum;
    private Integer pageSize;
    private Integer start;


}