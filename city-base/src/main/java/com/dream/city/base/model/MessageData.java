package com.dream.city.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WVv
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageData<T> {
    /**
     * 事件类型
     */
    String type;
    /**
     * 接收事件处理的模块
     */
    String model;
    /**
     * 具体业务数据
     */
    T data;

    /**
     * 操作状态码
     */
    private Integer code;


    public MessageData(String type,String model){
        this.type = type;
        this.model = model;
        this.data=null;
        this.code = 200;
    }

    public MessageData(String type,String model,Integer code){
        this.type = type;
        this.model = model;
        this.code = code;
        this.data = null;
    }

    public MessageData(String type,String model,T data){
        this.type = type;
        this.model = model;
        this.code = 200;
        this.data = data;
    }

}
