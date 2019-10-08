package com.dream.city.base.model.resp;

import lombok.Data;

import java.io.Serializable;

@Data
public class RuleResp implements Serializable {
    /**  */
    private Integer ruleId;

    /** 规则名称 */
    private String ruleName;

    /** 规则描述 */
    private String ruleDesc;

    /** 规则项目 */
    private Integer ruleItem;
    /** 规则项目名称 */
    private String itemName;


    /**  */
    private Double ruleRate;

    /** 规则优先级别 */
    private Byte raleLevel;

}