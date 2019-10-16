package com.dream.city.base.model.resp;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 玩家投资订单
 */
@ToString
@Data
public class InvestResp implements Serializable {

    private Integer inId;
    /** 名称 */
    private String inName;
    /** 限额 */
    private BigDecimal inLimit;
    /** 个人所得税 */
    private BigDecimal personalInTax;
    /** 企业所得税 */
    private BigDecimal enterpriseIntax;
    /** 定额税 */
    private BigDecimal inQuotaTax;
    private BigDecimal earnCurrent;
    private Integer isWithdrew;
    private String inImg;
    /** 收益倍数 */
    private Integer inEarning;
    private Integer inType;
    private String isValid;
    /** 开始时间 */
    private Date inStart;
    /** 投资结束时间 */
    private Date inEnd;

    private String orderState;

    /** 玩家ID */
    private String playerId;
    private String playerName;
    private String playerNick;



}
