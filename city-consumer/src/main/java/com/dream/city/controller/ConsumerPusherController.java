package com.dream.city.controller;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.service.ConsumerMessageService;
import com.dream.city.service.ConsumerPusherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wvv
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerPusherController {
    @Autowired
    ConsumerMessageService messageService;

    @Autowired
    ConsumerPusherService consumerPusherService;


    @RequestMapping("/job/push")
    public Result jobPush(@RequestBody Message message){

       return consumerPusherService.jobPush(message);
    }

    /**
     * 推送广播消息
     * @param message
     * @return
     */
    @RequestMapping("/job/notice")
    public Result noticePush(@RequestBody Message message){
        return consumerPusherService.noticePush(message);
    }

}
