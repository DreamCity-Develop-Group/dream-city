package com.dream.city.base.model.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Wvv
 */
@Data
public class SalesRefuseOrder {
    private Integer refuseId;
    private String refuseOrderOld;
    private String refuseOrderNew;
    private String refusePlayerBuyer;
    private String refusePlayerSeller;
    private Date createTime;
    private Date updateTime;

    public SalesRefuseOrder(){super();}
    public SalesRefuseOrder(String refuseOrderOld, String refuseOrderNew, String refusePlayerBuyer, String refusePlayerSeller) {
        this.refuseOrderOld = refuseOrderOld;
        this.refuseOrderNew = refuseOrderNew;
        this.refusePlayerBuyer = refusePlayerBuyer;
        this.refusePlayerSeller = refusePlayerSeller;
        this.createTime = new Date();
    }
}
