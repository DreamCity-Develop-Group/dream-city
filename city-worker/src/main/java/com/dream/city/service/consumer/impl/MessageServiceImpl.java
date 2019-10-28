package com.dream.city.service.consumer.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.utils.HttpClientUtil;
import com.dream.city.base.utils.RedisKeys;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    RedisUtils redisUtils;

    @LcnTransaction
    @Transactional
    @Override
    public boolean pushRetry(String clientId, Map data) throws BusinessException {
        //todo==1、找出并构造需要推送的记录===
        Message msg = new Message();

        msg.setCreatetime(String.valueOf(System.currentTimeMillis()));
        msg.setDesc("后台任务："+data.get("jobGroupName")+"["+data.get("jobName")+"]发起推送到["+clientId+"]！");
        msg.setSource("worker");
        String cId = redisUtils.get(RedisKeys.PLAYER_ONLINE_STATE_KEY+data.get("username")).get();
        msg.setTarget(cId);
        msg.setData(new MessageData("jobPush","consumer",data.get("data")));
        //Message message = JsonUtil.parseJsonToObj(data.toString(),Message.class);

        //todo==2、发起推送===
        HttpClientUtil.post(msg);
        return false;
    }
}
