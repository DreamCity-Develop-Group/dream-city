package com.dream.city.player.controller;


import com.dream.city.base.model.Page;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Friends;
import com.dream.city.player.service.FriendsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
    public Result<Boolean> addFriend(@RequestParam("playerId") String playerId,
                                     @RequestParam("friendId") String friendId){
        logger.info("addFriend，playerId：{},friendId:{}",playerId,friendId);
        Friends friend = getFriendFromUsername(friendId,playerId);
        boolean addFriend = friendsService.addFriend(friend);
        return new Result<>(addFriend,"添加好友");
    }


    private Friends getFriendFromUsername(String playerId,String friendId){
        Friends friendReq = new Friends();
        friendReq.setPlayerId(playerId);
        friendReq.setFriendId(friendId);
        Friends friend = friendsService.selectByPlayerIdFriendId(friendReq);
        return friend;
    }

    /**
     * 同意添加好友
     * @param playerId
     * @param friendId
     * @return
     */
    @RequestMapping("/agreeAddFriend")
    public Result<Boolean> agreeAddFriend(@RequestParam("playerId") String playerId,
                           @RequestParam("friendId") String friendId,@RequestParam("agree") String agree){
        logger.info("agreeAddFriend，playerId：{},friendId:{}",playerId,friendId);
        Friends friend = getFriendFromUsername(playerId,friendId);
        if ("agreed".equalsIgnoreCase(agree)){
            friend.setAgree(1);
        }
        boolean agreeAddFriend = friendsService.agreeAddFriend(friend);
        return new Result<>(agreeAddFriend,"同意添加好友");
    }

    /**
     * 好友列表
     * @param pageReq
     * @return
     */
    @RequestMapping("/friendList")
    public Result<Page> friendList(@RequestBody PageReq pageReq){
        logger.info("friendList，pageReq：{}",pageReq);
        Page page = friendsService.friendList(pageReq);
        return new Result<>(Boolean.TRUE,"好友列表",page);
    }


    /**
     * 好友申请列表
     * @param pageReq
     * @return
     */
    @RequestMapping("/applyFriendList")
    public Result<Page> applyFriendList(@RequestBody PageReq pageReq){
        logger.info("applyFriendList，pageReq：{}",pageReq);
        Page page = friendsService.applyFriendList(pageReq);
        return new Result<>(Boolean.TRUE,"好友申请列表",page);
    }



}
