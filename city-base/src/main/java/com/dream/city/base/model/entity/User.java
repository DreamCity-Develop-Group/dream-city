package com.dream.city.base.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Wvv
 */
@Data
public class User implements Serializable {

    private String uId;
    private String uName;
    private String uPass;
    private String uNick;
    private String uInvite;

    public User(String uId, String uName, String uPass, String nick, String invite){
        this.uId =uId;
        this.uName = uName;
        this.uPass = uPass;
        this.uNick = invite;
        this.uInvite = nick;
    }
    public User(){}
}
