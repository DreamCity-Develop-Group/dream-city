package com.dream.city.controller;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.service.consumer.ConsumerMessageService;
import com.dream.city.service.consumer.ConsumerPusherService;
import com.dream.city.service.routes.PusherService;
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
    PusherService pusherService;


    @RequestMapping("/job/push")
    public Result jobPush(@RequestBody Message msg){

        try {
            return pusherService.jobPush(msg);
        }catch (Exception e){
            return Result.result(false);
        }
    }

    /**
     * 推送广播消息
     * @param msg
     * @return
     */
    @RequestMapping("/job/notice")
    public Result noticePush(@RequestBody Message msg){
        try {
            return pusherService.noticePush(msg);
        }catch (Exception e){
            return Result.result(false);
        }
    }

}
