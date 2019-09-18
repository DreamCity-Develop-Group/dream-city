package com.dream.city.controller;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.service.CityMessageService;
import com.dream.city.service.CityUserService;
import com.dream.city.service.ConsumerPlayerService;
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
    CityMessageService messageService;

    @Autowired
    ConsumerPusherService consumerPusherService;


    @RequestMapping("/job/push")
    public Result jobPush(@RequestBody Message message){

       return consumerPusherService.jobPush(message);
    }

}
