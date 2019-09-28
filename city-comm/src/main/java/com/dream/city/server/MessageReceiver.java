package com.dream.city.server;

import org.springframework.stereotype.Component;

/**
 * @author Wvv
 */
//@Component
public class MessageReceiver {
    /**
     * 接收消息的方法
     */
    public void receiveMessage1(String message) {
        System.out.println("收到一条消息：" + message);
    }

    /**
     * 接收消息的方法
     */
    public void receiveMessage2(String message) {
        System.out.println("收到一条消息2：" + message);
    }

}
