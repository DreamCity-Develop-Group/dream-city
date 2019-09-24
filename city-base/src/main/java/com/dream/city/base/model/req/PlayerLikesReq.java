package com.dream.city.base.model.req;

import java.io.Serializable;
import java.util.Date;

public class PlayerLikesReq implements Serializable {

    private Integer likedId;

    //收获点赞玩家ID
    private String likedPlayerId;

    //点赞玩家ID
    private String likePlayerId;

    private Integer likedInvestId;

    private Integer likedInvestTotal;

    private Date createTime;

    private Date updateTime;

    public Integer getLikedId() {
        return likedId;
    }

    public void setLikedId(Integer likedId) {
        this.likedId = likedId;
    }

    public String getLikedPlayerId() {
        return likedPlayerId;
    }

    public void setLikedPlayerId(String likedPlayerId) {
        this.likedPlayerId = likedPlayerId == null ? null : likedPlayerId.trim();
    }

    public Integer getLikedInvestId() {
        return likedInvestId;
    }

    public void setLikedInvestId(Integer likedInvestId) {
        this.likedInvestId = likedInvestId;
    }

    public Integer getLikedInvestTotal() {
        return likedInvestTotal;
    }

    public void setLikedInvestTotal(Integer likedInvestTotal) {
        this.likedInvestTotal = likedInvestTotal;
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

    public String getLikePlayerId() {
        return likePlayerId;
    }

    public void setLikePlayerId(String likePlayerId) {
        this.likePlayerId = likePlayerId;
    }
}