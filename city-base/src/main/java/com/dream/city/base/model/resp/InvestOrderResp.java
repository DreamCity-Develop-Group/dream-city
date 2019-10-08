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
public class InvestOrderResp implements Serializable {

    /**
     * 订单id
     */
    private Integer orderId;

    /** 项目ID */
    private Integer investId;

    /** 名称 */
    private String inName;

    /** 玩家ID */
    private String payerId;
    private String playerName;
    private String playerNick;

    /** 订单状态 */
    private String orderState;

    /** 订单是否复投 */
    private int orderRepeat;

    /** 限额 */
    private Float inLimit;

    /** 税金 定额税*/
    private BigDecimal inTax;

    /** 个人所得税 */
    private BigDecimal personalInTax;
    /** 企业所得税 */
    private BigDecimal enterpriseIntax;
    /** 获利 */
     private BigDecimal profit;

    /** 收益倍数 */
    private String inEarning;


    /** 投资金额 */
    private BigDecimal orderAmount;


    /** 审核状态(待审核wait，审核中verifying，pass审核通过，notpass审核不通过)*/
    private String verifyStatus;

    /** 审核意见 */
    private String verifyDesc;

    /**  */
    private String createTime;

    /**  */
    private String updateTime;
}
