package com.dream.city.base.model.enu;

/**
 * 审核状态（冻结freeze，已出账out，已入账in）
 */
public enum TradeStatus {

    FREEZE("FREEZE","冻结"),
    UNFREEZE("UNFREEZE","解冻"),
    OUT("OUT","已出账"),
    IN("IN","已入账");

    private String code;
    private String desc;

    TradeStatus(String code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
