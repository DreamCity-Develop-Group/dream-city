package com.dream.city.base.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wvv
 * 玩家项目收益
 */
@Data
public class EarnFalldownLog implements Serializable {
    /**  */
    private Integer fallId;
    /**
     * 投资项目ID
     */
    private Integer fallInvestId;

    /** 玩家ID */
    private String fallPlayerId;

    /** 掉落额度 */
    private BigDecimal fallAmount;

    private Date createTime;

}