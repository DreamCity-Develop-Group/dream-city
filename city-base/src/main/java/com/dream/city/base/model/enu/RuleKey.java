package com.dream.city.base.model.enu;

public enum RuleKey {
    STAMP_SIGN("STAMP_SIGN","社交印记"),
    PROFIT_CACL("PROFIT_CACL","收益计算"),
    INVEST_ORDER("INVEST_ORDER","投资订单"),
    PlAYER_LEVEL("PlAYER_LEVEL","商会等级"),
    PROFIT_GRANT("PROFIT_GRANT","利益分配"),
    SALES_OVERTIME("SALES_OVERTIME","订单超时"),
    IN("IN","已入账");

    private String key;
    private String desc;

    RuleKey(String key,String desc){
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }
}
