package com.dream.city.controller;


import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.utils.RedisKeys;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.ConsumerGameSettingService;
import com.dream.city.service.ConsumerPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/consumer")
public class ConsumerGameSettingController {

    @Autowired
    private ConsumerGameSettingService consumerGameSettingService;
    @Autowired
    private ConsumerPlayerService consumerPlayerService;
    @Autowired
    private RedisUtils redisUtils;


    @RequestMapping("/voice")
    public Object voice(@RequestBody Message msg){
        Map<String,Object> data = (Map<String, Object>) msg.getData().getT();
        String game = data.get("game").toString();
        String bg = data.get("bg").toString();
        String token = data.get("token").toString();
        String username = data.get("username").toString();

        String str = redisUtils.getStr(RedisKeys.CURRENT_USER + username);
        JSONObject userJson = JSONObject.parseObject(str,JSONObject.class);
        String userId = userJson.get("playerId").toString();
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
