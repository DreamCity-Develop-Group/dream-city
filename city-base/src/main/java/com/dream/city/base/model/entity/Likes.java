package com.dream.city.base.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Likes implements Serializable {
    private Long likeId;
    /**
     * 收获点赞玩家
     */
    private String likedPlayerId;
    /**
     * 点赞的项目
     */
    private Integer likedInvestId;

    /**
     * 当前玩家当前项目收获的点赞总数
     */
    private Integer likedGetTotal;

    /**
     * 当前玩家当前项目付出的点赞总数
     */
    private Integer likedSetTotal;

    private Date createTime;
    private Date updateTime;
}