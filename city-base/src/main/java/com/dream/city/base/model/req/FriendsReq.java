package com.dream.city.base.model.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class FriendsReq implements Serializable {

    private Long id;

    private String playerId;
    private String playerName;
    private String playerNick;
    private String playerImgurl;

    private String friendId;
    private String friendName;
    private String friendNick;
    private String friendImgurl;

    private Integer agree;

    private String invite;

}