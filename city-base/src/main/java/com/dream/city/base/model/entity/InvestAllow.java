package com.dream.city.base.model.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author Wvv
 */
@Data
public class InvestAllow {
    private Integer id;
    private String playerId;
    private String allowed;
    private BigDecimal amount;
    private Timestamp createTime;
}
