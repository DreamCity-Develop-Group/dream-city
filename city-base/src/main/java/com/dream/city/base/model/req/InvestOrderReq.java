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

    /**
     * 订单id
     */
    private Integer orderId;

    /** 项目ID */
    private Integer investId;

    /** 投资金额 */
    private BigDecimal orderAmount;

    /** 金额类型：mt，usdt */
    private String amountType;

    /** 名称 */
    private String inName;

    /** 玩家ID */
    private String payerId;

    /** 订单状态 */
    private String orderState;

    /** 订单是否复投 */
    private int orderRepeat;

    /** 限额 */
    private Float inLimit;

    /** 开始时间 */
    private Date inStart;

    /** 税金 */
    private Double inTax;

    /** 收益倍数 */
    private String inEarning;

    /** 投资结束时间 */
    private Date inEnd;

    /**  */
    private Date orderStartTime;

    /**  */
    private Date orderEndTime;
}
