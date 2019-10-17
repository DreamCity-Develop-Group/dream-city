package com.dream.city.base.model;


import com.dream.city.base.model.enu.ReturnStatus;
import lombok.Data;
import lombok.ToString;
import sun.java2d.loops.GeneralRenderer;

import java.io.Serializable;

/**
 * @author  Wvv
 */
@Data
@ToString
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

    //操作状态码
    private Integer code;

    public Message() {
    }

    public Message(String source, String target,
                   MessageData data) {
        super();
        this.source = source;
        this.target = target;
        this.data = data;
        this.desc = "";
        this.code = ReturnStatus.SUCCESS.getStatus();
        this.createtime = String.valueOf(System.currentTimeMillis());
    }

    public Message(String source, String target,
                   MessageData data,String desc) {
        super();
        this.source = source;
        this.target = target;
        this.data = data;
        this.desc = desc;
        this.code = ReturnStatus.SUCCESS.getStatus();
        this.createtime = String.valueOf(System.currentTimeMillis());
    }

    public Message(String source, String target,
                   MessageData data,String desc,
                   String createtime) {
        super();
        this.source = source;
        this.data = data;
        this.desc = desc;
        this.code = ReturnStatus.SUCCESS.getStatus();
        this.target = target;
        this.createtime = createtime;
    }

    public static Message generateMessage(Message msg,Result result){
        msg.setData(
                new MessageData(
                        msg.getData().getType(),
                        msg.getData().getModel(),
                        result.getData(),
                        result.getCode()
                )
        );
        msg.setDesc(result.getMsg());
        return msg;
    }
}
