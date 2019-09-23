package com.dream.city.base.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
public class AuthCode implements Serializable {
    private Long id;

    private String code;

    private String phone;

    private String valid;

    private Timestamp createTime;
}