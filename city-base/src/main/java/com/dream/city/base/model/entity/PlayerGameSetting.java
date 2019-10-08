package com.dream.city.base.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PlayerGameSetting implements Serializable {
    private Long id;

    private String playerId;

    private String type;

    private String val;

    private String status;

    private Date createDate;

    private Date updateDate;



}