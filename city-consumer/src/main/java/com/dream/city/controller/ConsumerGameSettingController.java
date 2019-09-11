package com.dream.city.controller;


import com.dream.city.base.model.Message;
import com.dream.city.service.ConsumerGameSettingService;
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

    @RequestMapping("/voice")
    public Object voice(@RequestBody Message msg){
        Map<String,Object> data = (Map<String, Object>) msg.getData().getT();
        String game = data.get("game").toString();
        String bg = data.get("bg").toString();
        String userId = data.get("id").toString();
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
