package com.dream.city.base.model.req;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wvv
 * 玩家投资订单
 */
@ToString
@Data
public class InvestOrderReq implements Serializable {

    private Integer id;

    /**
     * 订单id
     */
    private Integer orderId;
    /** 投资金额 */
    private BigDecimal orderAmount;
    /** 订单状态 */
    private String orderState;
    private String orderName;
    private String orderNum;
    /** 订单是否复投 */
    private Integer orderRepeat;



    /** 名称 */
    private String inName;
    /** 项目ID */
    private Integer investId;
    /** 限额 */
    private BigDecimal inLimit;
    /** 个人所得税 */
    private BigDecimal personalInTax;
    /** 企业所得税 */
    private BigDecimal enterpriseIntax;
    /** 收益倍数 */
    private Integer inEarning;
    /** 开始时间 */
    private Date inStart;
    /** 投资结束时间 */
    private Date inEnd;
    private Integer inType;


    /** 玩家ID */
    private String playerId;
    /** 玩家Name */
    private String payerName;


    private String verifyStatus;


    /** 金额类型：mt，usdt */
    private String amountType;
    private Date orderStartTime;
    private Date orderEndTime;






}
