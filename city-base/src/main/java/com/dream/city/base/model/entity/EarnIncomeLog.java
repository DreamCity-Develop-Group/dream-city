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
public class EarnIncomeLog implements Serializable {
    /**  */
    private Integer inLogId;
    /**
     * 投资项目ID
     */
    private Integer inInvestId;

    /** 玩家ID */
    private String inPlayerId;

    /** 本次收益额度 */
    private BigDecimal inAmount;

    private Date createTime;

}