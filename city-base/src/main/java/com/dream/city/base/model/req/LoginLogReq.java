package com.dream.city.base.model.req;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class LoginLogReq implements Serializable {

    private Long id;

    private String playerId;
    private String playerName;
    private String playerNick;
    private String playerInvite;

    private String imei;

    private String ip;

    private String descr;

    private String type;

    private Date createTime;



}