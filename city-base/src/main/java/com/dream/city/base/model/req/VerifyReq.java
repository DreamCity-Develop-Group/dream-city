package com.dream.city.base.model.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class VerifyReq implements Serializable {

    /**
     * 投资项目ID
     */
    private Integer investId;

    /** 交易id(交易记录表) */
    private Integer tradeId;

    private Integer orderId;

    private Integer earnId;

    private Integer verifyId;

    /**  审核人id(员工表) */
    private Integer empId;

    /** 审核状态(待审核wait，审核中verifying，pass审核通过，notpass审核不通过)*/
    private String verifyStatus;

    /** 审核意见 */
    private String verifyDesc;

    /** 资金类型（usdt投资:usdt_invest，mt投资:mt_invest，转账:transfer，提现:withdraw,usdt投资收益:usdt_earnings,mt投资收益:mt_earnings,usdt投资税金:usdt_invest_tax） */
    private String amountType;


}