package com.dream.city.domain;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author  Wvv
 */
@Data
public class Message implements Serializable{

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


    /*public Message(String sourse, String messageType, String msgContent,
                       String target, String infoSourceIP, String createtime,
                       String otherContent) {
        super();
        this.sourse = sourse;
        this.messageType = messageType;
        this.msgContent = msgContent;
        this.target = target;
        this.infoSourceIP = infoSourceIP;
        this.createtime = createtime;
        this.otherContent = otherContent;
    }*/

    /*@Override
    public String toString() {
        return "Message [sourse=" + sourse + ", messageType=" + messageType
                + ", msgContent=" + msgContent + ", target=" + target
                + ", infoSourceIP=" + infoSourceIP + ", createtime="
                + createtime + ", otherContent=" + otherContent + "]";
    }*/
}
