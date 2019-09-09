package com.dream.city.domain;

import lombok.Data;

/**
 * @author WVv
 */
@Data
public class MessageData<T> {
    //事件类型
    String type;
    //接收事件处理的模块
    String model;
    //具体业务数据
    T t;

    public MessageData(String type,String model,T t){
        this.type = type;
        this.model =model;
        this.t= t;
    }
}
