package com.dream.city.domain.entity;

import lombok.Data;

/**
 * @author Wvv
 */
@Data
public class User {

    private Integer uId;
    private String uName;
    private String uPass;
    private String uNick;
    private String uInvite;

    public User(Integer uId, String uName, String uPass, String nick, String invite){
        this.uId =uId;
        this.uName = uName;
        this.uPass = uPass;
        this.uNick = invite;
        this.uInvite = nick;
    }
    public User(){}
}
