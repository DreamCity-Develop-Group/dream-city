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
}
