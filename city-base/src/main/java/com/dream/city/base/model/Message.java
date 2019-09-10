package com.dream.city.base.model;


import lombok.Data;

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


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public MessageData getData() {
        return data;
    }

    public void setData(MessageData data) {
        this.data = data;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
