package com.dream.city.base.model.enu;

public enum OrderState {

    CANCEL(0,"取消"),

    //create(1,"新建"),

    PAID(2,"待支付"),

    PAY(3,"已支付"),

    TOBESHIPPED(4,"待发货"),

    SHIPPED(5,"已发货"),

    RECEIVED(6,"已收货"),

    CLOSE(7,"关闭"),

    INVALID(-1,"作废");

    // 成员变量
    private int status;
    private String name;
    private String desc;

    OrderState(int status, String name){
        this.name = name;
        this.status = status;
    }

    public String getCode() {
        return this.name();
    }
    public int getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
