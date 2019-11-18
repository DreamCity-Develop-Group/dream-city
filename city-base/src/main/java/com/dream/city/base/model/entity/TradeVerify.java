package com.dream.city.base.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class TradeVerify  implements Serializable {


    private Integer verifyId;

    /** 交易id(交易记录表) */
    private Integer verifyTradeId;
    private Integer verifyOrderId;

    /**  审核人id(员工表) */
    private Integer verifyEmpId;

    private String verifyToAddress;
    /** 审核状态(待审核wait，审核中verifying，pass审核通过，notpass审核不通过)*/
    private String verifyStatus;

    /** 审核意见 */
    private String verifyDesc;

    private Date createTime;

    private Date updateTime;
}