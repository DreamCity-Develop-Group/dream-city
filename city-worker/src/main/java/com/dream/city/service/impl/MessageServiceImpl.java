package com.dream.city.service.impl;

import com.dream.city.base.model.Message;
import com.dream.city.base.utils.HttpClientUtil;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    RedisUtils redisUtils;

    @Override
    public boolean pushRetry(String clientId, Map data) {
        //todo==1、找出需要推送的记录===
        Message message = JsonUtil.parseJsonToObj(data.toString(),Message.class);
        //todo==1、发起推送===
        HttpClientUtil.post(message);
        return false;
    }
}
