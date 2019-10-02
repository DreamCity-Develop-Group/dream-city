package com.dream.city.base.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class InvestRule implements Serializable {
    /**  */
    private Integer ruleId;

    private String ruleFlag;
    /**
     *
     */
    private String ruleOpt;

    /** 规则名称 */
    private String ruleName;

    /** 规则描述 */
    private String ruleDesc;

    /** 规则项目 */
    private Integer ruleItem;

    /**  */
    private Double ruleRate;

    /** 规则优先级别 */
    private Byte ruleLevel;

    /**  */
    private Date createTime;

    /**  */
    private Date updateTime;



}
