package com.dream.city.controller;

import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.Page;
import com.dream.city.service.ConsumerFriendsService;
import com.dream.city.service.ConsumerPlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/consumer")
public class ConsumerFriendsController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ConsumerFriendsService consumerFriendsService;
    @Autowired
    ConsumerPlayerService consumerPlayerService;

    @RequestMapping("/addfriend")
    public Message addFriend(@RequestBody Message msg){
        Map map = (Map)msg.getData().getT();
        //String nick = (String) map.get("nick");
        String playerId = (String) map.get("playerId");
        String friendId = (String) map.get("friendId");

        JSONObject jsonReq = new JSONObject();
        jsonReq.put("playerId",playerId);
        jsonReq.put("friendId",friendId);
        boolean b = consumerFriendsService.addFriend(jsonReq.toJSONString());

        return null;
    }


    @RequestMapping("/agreeAddFriend")
    public Message agreeAddFriend(@RequestBody Message msg){
        Map map = (Map)msg.getData().getT();
        String id = (String) map.get("id");
        boolean b = consumerFriendsService.agreeAddFriend(Long.valueOf(id));
        return null;
    }


    @RequestMapping("/friendList")
    public Message friendList(@RequestBody Message msg){
        Map map = (Map)msg.getData().getT();
        String playerId = (String) map.get("playerId");
        Page page = consumerFriendsService.friendList(playerId);
        return null;
    }


}
