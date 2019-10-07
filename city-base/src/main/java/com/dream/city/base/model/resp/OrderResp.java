package com.dream.city.base.model.resp;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderResp implements Serializable {
    /**  */
    private Integer orderId;

    /** 投资项目ID */
    private Integer orderInvestId;
    /** 投资项目名称 */
    private String inName;

    /** 玩家ID */
    private String orderPayerId;
    /** 玩家Name */
    private String payerName;

    /** 投资金额 */
    private BigDecimal orderAmount;

    private BigDecimal inTax;

    /** 状态 */
    private String orderState;

    /** 是否复投 */
    private int orderRepeat;

    /** 审核状态(待审核wait，审核中verifying，pass审核通过，notpass审核不通过)*/
    private String verifyStatus;

    /** 审核意见 */
    private String verifyDesc;

    /**  */
    private String createTime;

    /**  */
    private String updateTime;

}