package com.dream.city.base.model.resp;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PlayerLikesResp implements Serializable {

    private Integer likedId;

    private Integer logId;

    private String likedPlayerId;
    private String friendName;
    private String friendNick;


    private String likePlayerId;
    private String playerName;
    private String playerNick;

    private Integer likedInvestId;
    private String inName;

    private Integer likedGetTotal;
    private Integer likedSetTotal;

    private String createTime;

    private String updateTime;
}