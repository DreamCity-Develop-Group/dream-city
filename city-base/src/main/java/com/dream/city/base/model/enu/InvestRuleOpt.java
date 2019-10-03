package com.dream.city.base.model.enu;

/**
 * @author Wvv
 */
public enum InvestRuleOpt {

    /**
     * 按比率%
     */
    OPT_RATE(1,"按比率%"),

    OPT_SORT_TOP(2,"取前排数量"),

    OPT_SORT_END(3,"取后排数量"),

    OPT_ADD(4,"可提取");


    /**
     * 权重
     */
    private int weight;
    /**
     * 描述
     */
    private String desc;

    InvestRuleOpt(int weight, String desc){
        this.weight = weight;
        this.desc = desc;
    }

    public int getWeight() {
        return weight;
    }

    public String getDesc() {
        return desc;
    }
}
