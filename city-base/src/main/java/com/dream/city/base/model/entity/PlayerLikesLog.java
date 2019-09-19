package com.dream.city.player.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class PlayerLikesLog implements Serializable {

    private Integer likeId;

    private String likePlayerId;

    private String likeLikedId;

    private Integer likeInvestId;

    private Date createTime;

    private Date updateTime;

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }

    public String getLikePlayerId() {
        return likePlayerId;
    }

    public void setLikePlayerId(String likePlayerId) {
        this.likePlayerId = likePlayerId == null ? null : likePlayerId.trim();
    }

    public String getLikeLikedId() {
        return likeLikedId;
    }

    public void setLikeLikedId(String likeLikedId) {
        this.likeLikedId = likeLikedId == null ? null : likeLikedId.trim();
    }

    public Integer getLikeInvestId() {
        return likeInvestId;
    }

    public void setLikeInvestId(Integer likeInvestId) {
        this.likeInvestId = likeInvestId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}