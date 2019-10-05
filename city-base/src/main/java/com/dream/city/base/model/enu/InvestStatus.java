package com.dream.city.base.model.enu;

public enum InvestStatus {

    SUBSCRIBE(0,"预约"),

    SUBSCRIBED(1,"预约中"),

    SUBSCRIBE_PASS(2,"预约成功"),

    INVEST(3,"投资"),

    MANAGEMENT(4,"经营中"),

    EXTRACT(5,"可提取"),

    FINISHED(6,"已完成"),

    CANCEL(7,"取消"),

    SUBSCRIBE_VERIFY_FAIL(8,"预约审核不通过"),

    INVALID(-1,"作废");

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
