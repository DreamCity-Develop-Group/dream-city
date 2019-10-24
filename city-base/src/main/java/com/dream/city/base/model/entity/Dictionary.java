package com.dream.city.base.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Dictionary implements Serializable {

    private Integer id;

    private String name;

    private String key;

    private String val;

    private Byte isValid;

    private String descr;

    private Date createTime;

    private Date updateTime;

}