package com.dream.city.base.model.enu;

public enum InvestStatus {
    //预约、预约中、经营中、可提取
    SUBSCRIBE(0,"可预约"),

    SUBSCRIBED(1,"预约中"),

    MANAGEMENT(2,"经营中"),

    EXTRACT(3,"可提取"),

    FINISHED(4,"已完成");

    // 成员变量
    private int status;
    private String desc;

    InvestStatus(int status, String desc){
        this.status = status;
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
