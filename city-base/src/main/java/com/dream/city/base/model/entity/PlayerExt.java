package com.dream.city.base.model.entity;

import java.io.Serializable;
import java.util.Date;

public class PlayerExt implements Serializable {
    private Long id;

    private String playerId;

    private String friendlink;

    private String imgurl;

    private Date createDate;

    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId == null ? null : playerId.trim();
    }

    public String getFriendlink() {
        return friendlink;
    }

    public void setFriendlink(String friendlink) {
        this.friendlink = friendlink == null ? null : friendlink.trim();
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}