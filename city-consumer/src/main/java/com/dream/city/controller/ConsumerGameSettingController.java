package com.dream.city.controller;


import com.dream.city.base.model.Message;
import com.dream.city.service.handler.GameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "游戏设置", description = "游戏设置")
@RestController
@RequestMapping("/consumer")
public class ConsumerGameSettingController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    GameService gameService;

    @ApiOperation(value = "游戏设置", httpMethod = "POST", notes = "游戏音效，背景音效设置", response = Message.class)
    @RequestMapping("/voice")
    public Object voice(@RequestBody Message msg){
        try {
            return gameService.voice(msg);
        }catch (Exception e){
            return msg;
        }
    }

}
