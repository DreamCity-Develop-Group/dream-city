package com.dream.city.service.handler.impl;

import com.alibaba.fastjson.JSONObject;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.model.enu.RuleKey;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.base.utils.RedisKeys;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.consumer.*;
import com.dream.city.service.handler.PusherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class PusherServiceImpl implements PusherService {
    @Autowired
    ConsumerMessageService messageService;

    @Autowired
    ConsumerPusherService consumerPusherService;

    @Autowired
    RedisUtils redisUtils;
    @Autowired
    ConsumerTreeService treeService;
    @Autowired
    ConsumerSalesService salesService;
    @Autowired
    ConsumerRuleService consumerRuleService;


    @Async
    @LcnTransaction
    @Transactional
    @Override
    public void receive(Player player, int type) {
        if (player == null) {
            return;
        }
        boolean isPresent =  redisUtils.get(RedisKeys.PLAYER_ONLINE_STATE_KEY + player.getPlayerName()).isPresent();
        if(isPresent) {
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
            //兑换请求数据
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
                    Result numRules = consumerRuleService.getRuleItem(RuleKey.SALES_OVERTIME.getKey());



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
        }else{
            log.info("当前用户["+player.getPlayerName()+"]不在线");
        }

    }

    @LcnTransaction
    @Transactional
    @Override
    public Result jobPush(Message message) throws BusinessException{

        return consumerPusherService.jobPush(message);
    }

    /**
     * 推送广播消息
     * @param message
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Result noticePush(Message message) throws BusinessException {
        return consumerPusherService.noticePush(message);
    }
}
