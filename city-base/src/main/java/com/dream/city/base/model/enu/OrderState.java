package com.dream.city.base.model.enu;

<<<<<<< HEAD
public enum OrderState {

    CREATE(0,"创建"),

    WAITEPAID(1,"待支付"),

    PAID(2,"已支付"),

    OVERTIME(3,"超时"),

    CANCEL(4,"取消"),

    WAITESEND(5,"待发货"),
=======
/**
 * 改用 InvestStatus
 */
@Deprecated
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
>>>>>>> db5c5451a7fa6cc570942c5aae14293b74180dd3

    INVALID(-1,"作废");

    // 成员变量
    private int status;
<<<<<<< HEAD
    private String desc;

    OrderState(int status, String desc){
        this.status = status;
        this.desc = desc;
    }

=======
    private String name;
    private String desc;

    OrderState(int status, String name){
        this.status = status;
        this.name = name;
    }

    public String getCode() {
        return this.name();
    }
>>>>>>> db5c5451a7fa6cc570942c5aae14293b74180dd3
    public int getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
