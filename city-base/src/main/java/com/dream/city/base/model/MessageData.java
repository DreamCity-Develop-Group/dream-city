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
    //事件类型
    String type;
    //接收事件处理的模块
    String model;
    //具体业务数据
    T data;

    public MessageData(String type,String model){
        this.type = type;
        this.model = model;
    }
}
