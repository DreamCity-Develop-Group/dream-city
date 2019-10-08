package com.dream.city.base.model.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class PlayerGameSettingReq implements Serializable {

    private Long id;

    private String playerId;
    private String playerNick;

    private String type;

    private String val;

    private String status;


}