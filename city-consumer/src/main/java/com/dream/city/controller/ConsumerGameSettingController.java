package com.dream.city.controller;


import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.base.utils.RedisKeys;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.ConsumerGameSettingService;
import com.dream.city.service.ConsumerPlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(value = "游戏设置", description = "游戏设置")
@RestController
@RequestMapping("/consumer")
public class ConsumerGameSettingController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConsumerGameSettingService consumerGameSettingService;
    @Autowired
    private ConsumerPlayerService consumerPlayerService;
    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation(value = "游戏设置", httpMethod = "POST", notes = "游戏音效，背景音效设置", response = Message.class)
    @RequestMapping("/voice")
    public Object voice(@RequestBody Message msg){
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
