package com.dream.city.base.model.resp;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class LoginLogResp implements Serializable {

    private Long id;

    private String playerId;
    private String playerName;
    private String playerNick;
    private String playerInvite;
    private String imgurl;

    private String imei;

    private String ip;

    private String descr;

    private String type;

    private Date createTime;



}