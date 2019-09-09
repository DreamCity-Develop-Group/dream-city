package com.dream.city.domain;

import lombok.Data;

/**
 * @author Wvv
 */
@Data
public class ApiReturnObject<T> {
    private Integer code;
    private String msg;
    private Message data;

    ApiReturnObject() {
        this.code = 0;
        this.msg = "";
        this.data = null;
    }

    public ApiReturnObject(String msg) {
        this.msg = msg;
    }

    ApiReturnObject(Integer code) {
        this.code = code;
    }

    ApiReturnObject(Message data) {
        this.data = data;
    }
}
