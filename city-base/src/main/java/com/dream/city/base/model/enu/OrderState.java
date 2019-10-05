package com.dream.city.base.model.enu;

public enum OrderState {

    CREATE(0,"创建"),

    WAITEPAID(1,"待支付"),

    PAID(2,"已支付"),

    OVERTIME(3,"超时"),

    CANCEL(4,"取消"),

    WAITESEND(5,"待发货"),

    INVALID(-1,"作废");

    // 成员变量
    private int status;
    private String desc;

    OrderState(int status, String desc){
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
