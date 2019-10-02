package com.dream.city.base.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Wvv
 */
@Data
public class PlayerLikes implements Serializable {

    private Integer likedId;

    private String likedPlayerId;

    private Integer likedInvestId;

    private Integer likedGetTotal;
    private Integer likedSetTotal;

    private Date createTime;

    private Date updateTime;

}