package com.dream.city.domain;


import lombok.Data;

import java.io.Serializable;

/**
 * @author  Wvv
 */
@Data
public class Message<T> implements Serializable{

    private static final long serialVersionUID = -6451812593150428369L;

    /**
     * 信息来源
     */
    private String source;
    // 消息数据
    private MessageData data;
    // 发送目的地
    private String target;
    // 消息时间
    private String createtime;
    // 其他信息
    private String desc;


    public Message(String source, String target,
                   MessageData data,String desc,
                       String createtime) {
        super();
        this.source = source;
        this.data = data;
        this.desc = desc;
        this.target = target;
        this.createtime = createtime;
    }

    /*@Override
    public String toString() {
        return "Message [sourse=" + sourse + ", messageType=" + messageType
                + ", msgContent=" + msgContent + ", target=" + target
                + ", infoSourceIP=" + infoSourceIP + ", createtime="
                + createtime + ", otherContent=" + otherContent + "]";
    }*/
}
