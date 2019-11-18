package com.dream.city.server;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.util.JsonUtil;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Wvv
 */
@Component
@Slf4j
public class MessageReceiver {
    @Autowired
    PublishServer publishServer;
    /**
     * 接收消息的方法
     */
    public void receiveMessageTip(String message) throws InterruptedException {
        System.out.println("收到一条消息1：" + message);
        log.info("现在开始推送信息");
        //JsonUtil.
        String msg = message.replace("\\","");
        msg = msg.substring(1,msg.length()-1);

        com.dream.city.base.model.Message myMsg = JsonUtil.parseJsonToObj(msg, com.dream.city.base.model.Message.class);
        //Message msg = JsonUtil.parseJsonToObj(message,Message.class);
        //消息重复标识
        Integer current= Integer.getInteger(System.currentTimeMillis()+"");

        String type = myMsg.getData().getType();
        myMsg.setCode(current);

        publishServer.pushToClient(myMsg);
        log.info("第一次推送信息");
        Thread.sleep(2000);
        new Thread(()->{
            log.info("第二次线程["+Thread.currentThread()+"]发出推送信息");
            publishServer.pushToClient(myMsg);
        },"pushToClient").start();
    }

    /**
     * 接收消息的方法
     */
    public void receiveMessage2(String message) {
        System.out.println("收到一条消息2：" + message);
    }

}
