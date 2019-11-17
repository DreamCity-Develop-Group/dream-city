package com.dream.city.base.model.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

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
    private Timestamp createTime;
    private Timestamp updateTime;

    public SalesRefuseOrder(){super();}
    public SalesRefuseOrder(String refuseOrderOld, String refuseOrderNew, String refusePlayerBuyer, String refusePlayerSeller) {
        this.refuseOrderOld = refuseOrderOld;
        this.refuseOrderNew = refuseOrderNew;
        this.refusePlayerBuyer = refusePlayerBuyer;
        this.refusePlayerSeller = refusePlayerSeller;
    }
}
