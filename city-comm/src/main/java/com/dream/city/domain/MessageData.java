package com.dream.city.domain;

import lombok.Data;
import lombok.ToString;

/**
 * @author WVv
 */
@Data
@ToString
public class MessageData<T> {
    //事件类型
    String type;
    //接收事件处理的模块
    String model;
    //具体业务数据
    T t;
}
