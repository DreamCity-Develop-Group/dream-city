package com.dream.city.domain.vo;

import lombok.Data;
import lombok.val;

/**
 * @author Wvv
 */
@Data
public class ValiCode {
    //手机号码
    String phone;
    //验证码
    String valiCode;

    public ValiCode(String phone, String valiCode) {
        this.phone = phone;
        this.valiCode = valiCode;
    }
}
