package com.dream.city.base.model.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class RuleReq implements Serializable {
    /**  */
    private Integer ruleId;

    /** 规则名称 */
    private String ruleName;

    /** 规则项目 */
    private Integer ruleItem;
    /** 规则项目名称 */
    private String itemName;

    /** 规则描述 */
    private String ruleDesc;

    /**  */
    private Double ruleRate;

    /** 规则优先级别 */
    private Byte raleLevel;

}