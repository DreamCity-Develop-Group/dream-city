package com.dream.city.player.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Page;
import com.dream.city.base.model.req.UserReq;
import com.dream.city.player.domain.entity.Friends;
import com.dream.city.player.service.FriendsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/friends")
public class FriendsController {

    @Autowired
    private FriendsService friendsService;


    /**
     * 添加好友
     * @param jsonReq
     * @return
     */
    @RequestMapping("/addFriend")
    boolean addFriend(@RequestParam("json")String jsonReq){
        JSONObject map = JSON.parseObject(jsonReq, JSONObject.class);
        String playerId = (String) map.get("playerId");
        String friendId = (String) map.get("friendId");

        Friends friend = new Friends();
        friend.setPlayerId(playerId);
        friend.setFriendId(friendId);
        return friendsService.addFriend(friend);
    }


    /**
     * 同意添加好友
     * @param id
     * @return
     */
    @RequestMapping("/agreeAddFriend")
    boolean agreeAddFriend(@RequestParam("id")Long id){
        return friendsService.agreeAddFriend(id);
    }

    /**
     * 好友列表
     * @param playerId
     * @return
     */
    @RequestMapping("/friendList")
    Page friendList(@RequestParam("playerId") String playerId){
        if (StringUtils.isBlank(playerId)) {
            return new Page();
        }
        return friendsService.friendList(playerId);
    }



}
