package com.dream.city.domain.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Wvv
 */
@Data
public class UserReq implements Serializable {

    private String playerId;

    private String username;

    private String nick;

    private String userpass;

    private String pwshop;

    private String invited;

    public UserReq(){
    }

    public UserReq(String playerId, String username, String userpass){
        this.playerId =playerId;
        this.username = username;
        this.userpass = userpass;
    }

    public UserReq(String playerId, String username, String userpass, String nick, String invited){
        this.playerId =playerId;
        this.username = username;
        this.userpass = userpass;
        this.nick = nick;
        this.invited = invited;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public String getPwshop() {
        return pwshop;
    }

    public void setPwshop(String pwshop) {
        this.pwshop = pwshop;
    }

    public String getInvited() {
        return invited;
    }

    public void setInvited(String invited) {
        this.invited = invited;
    }
}
