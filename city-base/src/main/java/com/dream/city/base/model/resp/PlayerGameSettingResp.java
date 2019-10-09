package com.dream.city.base.model.resp;

import lombok.Data;

import java.io.Serializable;

@Data
public class PlayerGameSettingResp implements Serializable {

    private Long id;

    private String playerId;
    private String playerNick;
    private String playerName;
    private String imgurl;

    private String type;

    private String val;

    private String status;



}