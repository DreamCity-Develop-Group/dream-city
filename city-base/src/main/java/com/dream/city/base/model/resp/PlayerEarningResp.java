package com.dream.city.base.model.resp;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wvv
 * 玩家项目收益
 */
@Data
public class PlayerEarningResp implements Serializable {

    /**  */
    private Integer earnId;
    /**
     * 投资项目ID
     */
    private Integer earnInvestId;

    /** 玩家ID */
    private String earnPlayerId;

    /** 预计最大收益，达到就可以出局 */
    private BigDecimal earnMax;

    /**
     * 当前获利
     */
    private BigDecimal earnCurrent;

    /** 个税税金 */
    private BigDecimal earnPersonalTax;

    /** 定额税 */
    private BigDecimal earnQuotaTax;


    /**
     * 企业税金
     */
    private BigDecimal earnEnterpriseTax;

    /**
     * 是否已经可以提取 状态：0投资中1可提取2有掉落3提取完成
     */
    private Integer isWithdrew;

    private Date createTime;
    private Date updateTime;


    private String inName;
    private Integer inType;
    private BigDecimal inLimit;


}