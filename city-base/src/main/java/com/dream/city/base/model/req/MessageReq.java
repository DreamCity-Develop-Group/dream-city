package com.dream.city.base.model.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageReq implements Serializable {


    private Long id;

    private String playerId;
    private String playerNick;

    private String friendId;
    private String friendNick;

    private String title;
    private String content;

    private Integer haveRead;

    private String sendTime;

    //查询几天内的
    private Integer dayParam;

}