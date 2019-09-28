package com.dream.city.base.model.enu;

/**
 * 审核状态（冻结freeze，已出账out，已入账in）
 */
public enum TradeVerifyStatus {

    freeze("冻结"),
    out("已出账"),
    in("已入账");

    private String desc;

    TradeVerifyStatus(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
