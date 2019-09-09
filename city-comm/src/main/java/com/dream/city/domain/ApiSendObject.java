package com.dream.city.domain;

import lombok.Data;

/**
 * @author Wvv
 */
@Data
public class ApiSendObject {
    /**
     * 推送码
     */
    private Integer code;
    private String msg;

    ApiSendObject(){
        this.code = 0;
        this.msg = "";
    }

    public ApiSendObject(String msg){
        this.msg = msg;
    }

    ApiSendObject(Integer code){
        this.code = code;
    }
}
