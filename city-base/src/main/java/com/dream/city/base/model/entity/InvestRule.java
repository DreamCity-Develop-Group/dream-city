package com.dream.city.player.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class InvestRule implements Serializable {
    /**  */
    private Integer ruleId;

    /** 规则名称 */
    private String ruleName;

    /** 规则描述 */
    private String ruleDesc;

    /** 规则项目 */
    private Integer ruleItem;

    /**  */
    private Double ruleRate;

    /** 规则优先级别 */
    private Byte raleLevel;

    /**  */
    private Date createTime;

    /**  */
    private Date updateTime;

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc == null ? null : ruleDesc.trim();
    }

    public Integer getRuleItem() {
        return ruleItem;
    }

    public void setRuleItem(Integer ruleItem) {
        this.ruleItem = ruleItem;
    }

    public Double getRuleRate() {
        return ruleRate;
    }

    public void setRuleRate(Double ruleRate) {
        this.ruleRate = ruleRate;
    }

    public Byte getRaleLevel() {
        return raleLevel;
    }

    public void setRaleLevel(Byte raleLevel) {
        this.raleLevel = raleLevel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}