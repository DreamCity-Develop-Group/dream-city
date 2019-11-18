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

    private String inDec;

    private Date createTime;
    public EarnIncomeLog(){super();}

    public EarnIncomeLog(Integer inInvestId, String inPlayerId, BigDecimal inAmount,String inDec) {
        this.inLogId = 0;
        this.inInvestId = inInvestId;
        this.inPlayerId = inPlayerId;
        this.inAmount = inAmount;
        this.inDec = inDec;
        this.createTime = new Date();
    }
}