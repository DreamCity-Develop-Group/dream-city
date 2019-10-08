package com.dream.city.base.model.resp;

import lombok.Data;

import java.io.Serializable;

@Data
public class FriendsResp implements Serializable {

    private Long id;

    private String playerId;
    private String playerName;
    private String playerNick;

    private String friendId;
    private String friendName;
    private String friendNick;
    private String friendImgurl;

    private Integer agree;

    private String invite;

    private String createTime;
    private String updateTime;

}