package com.dream.city.base.model.enu;

/**
 * 审核状态（冻结freeze，已出账out，已入账in）
 */
public enum TradeStatus {

    FREEZE("冻结"),
    OUT("已出账"),
    IN("已入账");

    private String desc;

    TradeStatus(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
