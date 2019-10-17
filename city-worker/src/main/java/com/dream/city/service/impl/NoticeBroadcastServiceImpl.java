package com.dream.city.service.impl;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.utils.HttpClientUtil;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.MessageService;
import com.dream.city.service.NoticeBroadcastService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NoticeBroadcastServiceImpl implements NoticeBroadcastService {
    @Autowired
    RedisUtils redisUtils;

    @Override
    public boolean pushNoticeBroadcast(@NonNull String clients, Map data) {
        //todo==1、找出并构造需要推送的记录===
        Message msg = new Message();

        msg.setCreatetime(String.valueOf(System.currentTimeMillis()));
        msg.setDesc("后台任务："+data.get("jobGroupName")+"["+data.get("jobName")+"]发起推送到["+clients+"]！");
        msg.setSource("worker");
        //String cId = redisUtils.get(RedisKeys.PLAYER_ONLINE_STATE_KEY+data.get("username")).get();
        msg.setTarget(clients);
        msg.setData(new MessageData("jobPush","consumer",data.get("data")));
        //Message message = JsonUtil.parseJsonToObj(data.toString(),Message.class);

        //todo==2、发起推送===
        HttpClientUtil.post(msg);
        return false;
    }
}
