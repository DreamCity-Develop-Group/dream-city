package com.dream.city.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.utils.HttpClientUtil;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.base.utils.RedisKeys;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.MessageService;
import com.dream.city.service.PlayerAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {
    private final String PlAYER_PUSHER = "PUSHER_CHANNEL";

    @Autowired
    RedisUtils redisUtils;
    @Autowired
    private PlayerAccountService playerAccountService;

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

    @LcnTransaction
    @Transactional
    @Override
    public void pushJobMessage(String playerId, String desc) {
        Message message = new Message(
                "server",
                "client",
                new MessageData("push", "comm", new JSONObject(), ReturnStatus.SUCCESS.getStatus()),
                desc
        );
        Player player = playerAccountService.getPlayerByPlayerId(playerId);

        try {
            String clientId = redisUtils.get(RedisKeys.PLAYER_ONLINE_STATE_KEY + player.getPlayerName()).get();
            message.setTarget(clientId);

            //主动推送消息
            JSONObject falldownData = new JSONObject();


            falldownData.put("level", 1);
            falldownData.put("toPlayerId", playerId);
            falldownData.put("username", player.getPlayerName());
            falldownData.put("fromPlayerId", playerId);

            message.getData().setData(falldownData);
            String json = JsonUtil.parseObjToJson(message);

            redisUtils.publishMsg(PlAYER_PUSHER, json);
        }
        catch (Exception e){
            log.info(new Date() + " 推送消息错误： "+e.toString());
        }


    }
}
