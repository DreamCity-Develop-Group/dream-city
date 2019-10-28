package com.dream.city.controller;

import com.dream.city.base.model.Message;
import com.dream.city.service.handler.FriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "好友", description = "好友")
@RestController
@RequestMapping("/consumer/player/friend")
public class ConsumerFriendsController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FriendService friendService;

    /**
     * 好友主页
     * @param msg
     * @return
     */
    @ApiOperation(value = "好友主页", httpMethod = "POST", notes = "好友主页", response = Message.class)
    @RequestMapping("/friendHomePage")
    public Message friendHomePage(@RequestBody Message msg){
        try {
            return friendService.friendHomePage(msg);
        }catch (Exception e){
            return msg;
        }
    }



    @RequestMapping("/addFriend")
    @ApiOperation(value = "添加好友", httpMethod = "POST", notes = "添加好友", response = Message.class)
    public Message addFriend(@RequestBody Message msg){
        try {
            return friendService.addFriend(msg);
        }catch (Exception e){
            return msg;
        }
    }

    @ApiOperation(value = "通过好友", httpMethod = "POST", notes = "通过好友", response = Message.class)
    @RequestMapping("/agreeApply")
    public Message agreeApply(@RequestBody Message msg){
        try {
            return friendService.agreeApply(msg);
        }catch (Exception e){
            return msg;
        }
    }

    @ApiOperation(value = "获取好友列表", httpMethod = "POST", notes = "获取好友列表", response = Message.class)
    @RequestMapping("/friendList")
    public Message friendList(@RequestBody Message msg){
        try {
            return friendService.friendList(msg);
        }catch (Exception e){
            return msg;
        }
    }

    @ApiOperation(value = "获取好友申请列表", httpMethod = "POST", notes = "获取好友申请列表", response = Message.class)
    @RequestMapping("/applyFriend")
    public Message applyFriend(@RequestBody Message msg){
        try {
            return friendService.applyFriend(msg);
        }catch (Exception e){
            return msg;
        }
    }

}
