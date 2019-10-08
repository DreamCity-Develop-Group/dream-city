package com.dream.city.base.model.req;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PlayerLikesReq implements Serializable {

    private Integer likedId;

    //收获点赞玩家ID
    private String likedPlayerId;

    //点赞玩家ID
    private String likePlayerId;

    private Integer likedInvestId;

    private Date createTime;

    private Date updateTime;

    private Integer likedGetTotal;
    private Integer likedSetTotal;

}