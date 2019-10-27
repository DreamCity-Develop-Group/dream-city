package com.dream.city.service.routes.impl;

import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.service.consumer.ConsumerMessageService;
import com.dream.city.service.consumer.ConsumerPusherService;
import com.dream.city.service.routes.PusherService;
import org.springframework.beans.factory.annotation.Autowired;


public class PusherServiceImpl implements PusherService {
    @Autowired
    ConsumerMessageService messageService;

    @Autowired
    ConsumerPusherService consumerPusherService;


    public Result jobPush(Message message) throws BusinessException{

        return consumerPusherService.jobPush(message);
    }

    /**
     * 推送广播消息
     * @param message
     * @return
     */
    public Result noticePush(Message message) throws BusinessException {
        return consumerPusherService.noticePush(message);
    }
}
