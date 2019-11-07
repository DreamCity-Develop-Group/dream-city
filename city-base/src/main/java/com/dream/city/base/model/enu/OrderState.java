package com.dream.city.base.model.enu;

/**
 * 改用 InvestStatus
 */
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
    FINISHED(9,"完成"),
    EXPIRED(10,"过期"),
    REFUSE(11,"已拒绝"),
    REFUSE_EXPIRED_FINISHED(12,"已超时或拒绝但完成"),

    INVALID(-1,"作废");

    // 成员变量
    private int status;
    private String desc;

    OrderState(int status, String desc){
        this.status = status;
        this.desc = desc;
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
