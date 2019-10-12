package com.dream.city.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.model.enu.RuleKey;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.base.utils.RedisKeys;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.ConsumerTreeService;
import com.dream.city.service.PusherService;
import com.dream.city.service.RuleService;
import com.dream.city.service.SalesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisKeyCommands;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class PusherServer implements PusherService {
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    ConsumerTreeService treeService;
    @Autowired
    SalesService salesService;
    @Autowired
    RuleService ruleService;


    @Async
    @Override
    public void receive(Player player, int type) {
        if (player == null) {
            return;
        }
        String clientId = redisUtils.get(RedisKeys.PLAYER_ONLINE_STATE_KEY + player.getPlayerName()).get();
        Message message = new Message(
                "server",
                clientId,
                new MessageData(
                        "receive",
                        "player/dialog",
                        0,
                        ReturnStatus.MT_BUY_TIP.getStatus()
                ),
                "收取玩家个人消息",
                String.valueOf(System.currentTimeMillis())
        );
        Map<String, Integer> data = new HashMap();
        data.put("num",0);
        switch (type) {
            case 1:
                //取出兑换请求数据
                Result numRet = salesService.getSalesNum(player.getPlayerId());
                if (numRet.getSuccess()) {
                    int num = Integer.valueOf(numRet.getData().toString());

                    message.getData().setCode(ReturnStatus.MT_BUY_TIP.getStatus());
                    data.put("num",num);
                    message.getData().setData(data);
                }
                break;
            case 2:
                //取出超时的请求
                Result numOverTimeRet = salesService.getSalesNumOverTime(player.getPlayerId());
                //取出规则次数
                Result numRules = ruleService.getRuleItem(RuleKey.SALES_OVERTIME.getKey());



                if (numOverTimeRet.getSuccess() && numRules.getSuccess()) {
                    String jsonRule = JsonUtil.parseObjToJson(numRules.getData());
                    JSONObject jsonObject = JSONObject.parseObject(jsonRule);
                    data.put("num", Integer.valueOf(numOverTimeRet.getData().toString()));
                    data.put("hour", Integer.valueOf(jsonObject.get("hour").toString()));
                    data.put("rate", Integer.valueOf(jsonObject.get("rate").toString()));

                    message.getData().setCode(ReturnStatus.MT_MISS_BUY_TIP.getStatus());
                    message.getData().setData(data);
                }
                break;
            default:

        }
        if(data.get("num") > 0){
            String json = JsonUtil.parseObjToJson(message);
            redisUtils.publishMsg(RedisKeys.PLAYER_MESSAGE_CHANNEL, json);
            log.info("发出推送信息");
            try {
                Thread.sleep(2000);
                new Thread(()->{
                    log.info("第二次线程["+Thread.currentThread()+"]发出推送信息");
                    redisUtils.publishMsg(RedisKeys.PLAYER_MESSAGE_CHANNEL, json);
                },"publishMsg").start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
