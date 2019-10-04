package com.dream.city.base.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PlayerLikesLog implements Serializable {

    private Integer likeId;

    /**
     * 点赞玩家ID
     */
    private String likePlayerId;

    /**
     * 收获点赞玩家ID
     */
    private String likeLikedId;

    /**
     * 点赞投资ID
     */
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