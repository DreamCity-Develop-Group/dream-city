package com.dream.city.base.model.resp;

import lombok.Data;

import java.io.Serializable;

@Data
public class PlayerResp implements Serializable {

    private Long id;

    private String playerId;

    private String friendId;

    private String playerName;

    private String playerNick;

    private String playerPass;

    private String playerTradePass;

    private String playerInvite;

    private Integer playerLevel;

    private String playerLink;

    private String isValid;

    //玩家等级
    private String grade;

    //玩家积分
    private int integral;

    //商会成员数
    private int commerceMember;

    private String imgurl;

    private String createTime;

    private String updateTime;

    private String addfriend;

    private Integer agree;


}