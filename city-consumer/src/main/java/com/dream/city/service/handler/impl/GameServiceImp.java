package com.dream.city.service.handler.impl;

import com.alibaba.fastjson.JSONObject;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.base.utils.RedisKeys;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.consumer.ConsumerGameSettingService;
import com.dream.city.service.consumer.ConsumerPlayerService;
import com.dream.city.service.handler.GameService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author Wvv
 * @program: dream-city
 * @File: GameServiceImp
 * @description: 游戏设置服务类
 * @create: 2019/10/2019/10/27 22:46:22 [星期日]
 **/
@Service
public class GameServiceImp  implements GameService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConsumerGameSettingService consumerGameSettingService;
    @Autowired
    private ConsumerPlayerService consumerPlayerService;
    @Autowired
    private RedisUtils redisUtils;

    @LcnTransaction
    @Transactional
    @Override
    public Object voice(Message msg) throws BusinessException {
        logger.info("游戏设置", JSONObject.toJSONString(msg));
        Map<String,Object> data = (Map<String, Object>) msg.getData().getData();
        String game = data.get("game").toString();
        String bg = data.get("bg").toString();
        String username = data.get("username").toString();

        String redisKey = RedisKeys.CURRENT_USER + username;
        String userStr = null;
        if (redisUtils.hasKey(redisKey)){
            userStr = redisUtils.getStr(redisKey);
        }
        JSONObject userJson = null;
        String userId = null;
        if (StringUtils.isNotBlank(userStr)){
            userJson = JSONObject.parseObject(userStr,JSONObject.class);
            userId = userJson.get("playerId").toString();
        }

        if (StringUtils.isBlank(userId)){
            JSONObject playerNamejsonObject = new JSONObject();
            playerNamejsonObject.put("playerName",username);
            Result playerByName = consumerPlayerService.getPlayerByName(playerNamejsonObject.toJSONString());
            if (playerByName.getSuccess()){
                userJson = JSONObject.parseObject((String) playerByName.getData(),JSONObject.class);
                userId = userJson.get("playerId").toString();
            }
        }

        Object b = null;
        if (!StringUtils.isEmpty(game)){
            b =consumerGameSettingService.settingGameVioce(userId, Boolean.parseBoolean(game));
        }
        if (!StringUtils.isEmpty(bg)){
            b = consumerGameSettingService.settingGameVioce(userId,Boolean.parseBoolean(bg));
        }
        return b;
    }
}
