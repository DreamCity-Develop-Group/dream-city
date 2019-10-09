package com.dream.city.player.controller;


import com.dream.city.base.model.Page;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Friends;
import com.dream.city.player.service.FriendsService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


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
                                     @RequestParam("friendId") String friendId,
                                     @RequestParam("invite")String invite){
        logger.info("addFriend，playerId：{},friendId:{}",playerId,friendId);

        //判断是否存在好友关系: 作互为好友的判断，只要存在一条记录就表示已经是好友状态
        Friends friend = getFriendFromUsername(friendId,playerId);
        Friends friend1 = getFriendFromUsername(playerId,friendId);
        if(null == friend && friend1 ==null){
            friend = new Friends();
            friend.setAgree(0);
            friend.setCreateTime(new Date());
            friend.setFriendId(friendId);
            friend.setPlayerId(playerId);
            friend.setUpdateTime(new Date());
            friend.setInvite("");
            if (StringUtils.isNotBlank(invite)){
                friend.setInvite(invite);
            }
            boolean addFriend = friendsService.addFriend(friend);
            return Result.result(addFriend,"添加完成");
        }else{
           if (friend != null){
               if (friend.getAgree() ==1){
                   logger.info("已经是好友关系");
                   return Result.result(true,"已经是好友关系");
               }else if (friend.getAgree() == 0){
                   logger.info("已经添加好友关系");
                   return Result.result(true,"已经添加好友关系,待双方同意请求");
               }
               return Result.result(false);
           }else if (friend1 != null){
               if (friend1.getAgree()==1){
                   logger.info("已经是好友关系");
                   return Result.result(true,"已经是好友关系");
               }else if (friend1.getAgree() == 0){
                   logger.info("已经添加好友关系");
                   return Result.result(true,"已经添加好友关系,待双方同意请求");
               }

           }
        }
        return Result.result(false);
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
    public Result<PageInfo> friendList(@RequestBody Page pageReq){
        logger.info("friendList，pageReq：{}",pageReq);
        PageInfo page = friendsService.friendList(pageReq);
        return new Result<>(Boolean.TRUE,"好友列表",page);
    }


    /**
     * 好友申请列表
     * @param pageReq
     * @return
     */
    @RequestMapping("/applyFriendList")
    public Result<PageInfo> applyFriendList(@RequestBody Page pageReq){
        logger.info("applyFriendList，pageReq：{}",pageReq);
        PageInfo page = friendsService.applyFriendList(pageReq);
        return new Result<>(Boolean.TRUE,"好友申请列表",page);
    }



}
