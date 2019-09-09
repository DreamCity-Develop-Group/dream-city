package com.dream.city.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class Player implements Serializable {
    private Long id;

    private String playerId;

    private String username;

    private String nick;

    private String userpass;

    private String pwshop;

    private String invite;

    private String invited;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick == null ? null : nick.trim();
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass == null ? null : userpass.trim();
    }

    public String getPwshop() {
        return pwshop;
    }

    public void setPwshop(String pwshop) {
        this.pwshop = pwshop == null ? null : pwshop.trim();
    }

    public String getInvite() {
        return invite;
    }

    public void setInvite(String invite) {
        this.invite = invite == null ? null : invite.trim();
    }

    public String getInvited() {
        return invited;
    }

    public void setInvited(String invited) {
        this.invited = invited == null ? null : invited.trim();
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