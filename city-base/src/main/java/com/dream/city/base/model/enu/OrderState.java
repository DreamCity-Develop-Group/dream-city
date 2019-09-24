package com.dream.city.base.model.enu;

public enum OrderState {

    CANCEL(0,"取消"),

    CREATE(1,"新建"),

    PAID(2,"待支付"),

    PAY(3,"已支付"),

    WAITVERIFY(4,"待审核"),

    TOBESHIPPED(5,"待发货"),

    SHIPPED(6,"已发货"),

    RECEIVED(7,"已收货"),

    CLOSE(8,"关闭"),

    INVALID(-1,"作废");

    // 成员变量
    private int status;
    private String name;
    private String desc;

    OrderState(int status, String name){
        this.status = status;
        this.name = name;
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
