package com.dream.city.base.model.enu;

import lombok.Data;

/**
 * @author Wvv
 */
public enum ReturnStatus {
    SUCCESS(200,"成功"),
    NOT_ENOUGH(210,"USDT额度不足，请去充值"),
    NOT_ENOUGH_PARENT(211,"上级USDT额度不足"),
    TOKEN_EXPIRED(212,"TOKEN过期"),
    NOT_FINISHED(311,"未完成"),
    CLOSE(513,"已关闭"),
    INVALID(-1,"不可用"),
    ACCOUNT_PASS_REQUIRED(401,"请输入用户名和密码"),
    ERROR_PHONE(402,"请输入正确的手机号码"),
    PHONE_REQUIRED(403,"请输入手机号"),
    CODE_REQUIRED(404,"请输入验证码"),
    ERROR_CHARS(405,"8-16位字符,可包含数字,字母,下划线"),
    ERROR(500,"服务错误"),
    ACCOUNT_EXISTS(501,"账号存在"),
    NICK_EXISTS(502,"昵称存在"),
    ERROR_INVITE(503,"邀请码错误"),
    ERROR_CODE(504,"验证码错误"),
    CODE_EXPIRED(505,"验证码过期"),
    ERROR_ACCOUNT(506,"已账号或密码错误"),
    ERROR_RECEIVED(507,"获取失败"),
    ERROR_NOTEXISTS(508,"账号不存在"),
    SET_SUCCESS(200,"设置成功"),
    SET_FAILED(509,"设置失败"),
    WAITE_OPT(200,"操作成功，请耐心等待审核"),
    RETRY_OPT(511,"操作失败，请重试"),
    NEXT_OPT(512,"请完成下一步设置"),
    ERROR_PASS(514,"密码错误"),
    NOTSET_PASS(515,"密码未设置"),


    UPGRADE_TIP(666,"恭喜升级成功"),
    MT_MISS_BUY_TIP(602,"错过购买兑换处理提示"),
    MT_BUY_TIP(601,"购买兑换请求提示");

    /**
     * 成员变量
     */
    private int status;
    private String desc;

    /**
     * ReturnStatus
     * @param status
     * @param desc
     */
    ReturnStatus(int status, String desc){
        this.status = status;
        this.desc = desc;
    }

    /**
     * getCode
     * @return
     */
    public String getCode() {
        return this.name();
    }

    public int getStatus() {
        return status;
    }

    /**
     * getDesc
     *
     * @return
     */
    public String getDesc() {
        return desc;
    }
}
