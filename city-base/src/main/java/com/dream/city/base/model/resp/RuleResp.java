package com.dream.city.base.model.resp;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class RuleResp implements Serializable {


    /**  */
    private Integer ruleId;

    /**  */
    private String ruleFlag;

    /**  */
    private String ruleOptPre;

    /**  */
    private String ruleOpt;

    /** 规则名称 */
    private String ruleName;

    /** 规则描述 */
    private String ruleDesc;

    /** 规则项目 */
    private Integer ruleItem;
    /** 规则项目名称 */
    private String itemName;
    private String itemFlag;

    /**  */
    private BigDecimal ruleRatePre;

    /**  */
    private BigDecimal ruleRate;

    /** 规则优先级别 */
    private Integer ruleLevel;

    /**  */
    private String createTime;

    /**  */
    private String updateTime;




}