package com.dream.city.base.model.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Wvv
 */
@Data
public class UserReq implements Serializable {

    private String playerId;

    private String clientId;

    private String token;

    private String username;

    private String imgUrl;

    private String nick;

    private String userpass;

    private String oldpwshop;
    private String newpwshop;

    private String oldpw;
    private String newpw;

    private String invite;

    private String code;

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
        this.invite = invite;
    }

}
