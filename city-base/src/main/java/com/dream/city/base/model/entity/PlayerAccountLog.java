package com.dream.city.base.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Wvv
 */
@Data
public class PlayerAccountLog implements Serializable {
    private Long id;
    /**
     * 账户变化记录交易号
     */
    private int accId;
    private String playerId;

    private String address;

    private BigDecimal amountMt;
    private BigDecimal amountUsdt;
    private int type;
    private String desc;
    private Date createTime;
    public PlayerAccountLog(){super();}
    public PlayerAccountLog(String playerId,String address, BigDecimal amountUsdt,BigDecimal amountMt, int type, String desc) {
        this.playerId = playerId;
        this.address = address;
        this.amountUsdt = amountUsdt;
        this.amountMt = amountMt;
        this.type = type;
        this.desc = desc;
    }



}