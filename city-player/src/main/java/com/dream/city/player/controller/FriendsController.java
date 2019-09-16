package com.dream.city.player.controller;


import com.dream.city.base.model.Page;
import com.dream.city.player.domain.entity.Friends;
import com.dream.city.player.service.FriendsService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/friends")
public class FriendsController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FriendsService friendsService;



    /**
     * 添加好友
     * @param playerId
     * @param friendId
     * @return
     */
    @RequestMapping("/addFriend")
    boolean addFriend(@RequestParam("playerId") String playerId,
                      @RequestParam("friendId") String friendId){
        logger.info("addFriend，playerId：{},friendId:{}",playerId,friendId);
        Friends friend = getFriendFromUsername(playerId,friendId);
        return friendsService.addFriend(friend);
    }


    private Friends getFriendFromUsername(String playerId,String friendId){
        Friends friend = new Friends();
        friend.setPlayerId(playerId);
        friend.setFriendId(friendId);
        return friend;
    }

    /**
     * 同意添加好友
     * @param playerId
     * @param friendId
     * @return
     */
    @RequestMapping("/agreeAddFriend")
    boolean agreeAddFriend(@RequestParam("playerId") String playerId,
                           @RequestParam("friendId") String friendId){
        logger.info("agreeAddFriend，playerId：{},friendId:{}",playerId,friendId);
        Friends friend = getFriendFromUsername(playerId,friendId);
        return friendsService.agreeAddFriend(friend);
    }

    /**
     * 好友列表
     * @param playerId
     * @return
     */
    @RequestMapping("/friendList")
    Page friendList(@RequestParam("playerId") String playerId){
        logger.info("friendList，playerId：{}",playerId);
        if (StringUtils.isBlank(playerId)) {
            return new Page();
        }
        return friendsService.friendList(playerId);
    }


    /**
     * 好友申请列表
     * @param playerId
     * @return
     */
    @RequestMapping("/applyFriendList")
    Page applyFriendList(@RequestParam("playerId") String playerId){
        logger.info("applyFriendList，playerId：{}",playerId);
        if (StringUtils.isBlank(playerId)) {
            return new Page();
        }
        return friendsService.applyFriendList(playerId);
    }



}
