package com.dream.city.player.controller;


import com.dream.city.base.model.Page;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Friends;
import com.dream.city.base.model.entity.PlayerLikes;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.model.req.FriendsReq;
import com.dream.city.base.model.resp.FriendsResp;
import com.dream.city.player.service.FriendsService;
import com.dream.city.player.service.LikesService;
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
import java.util.List;


@RestController
@RequestMapping("/friends")
public class FriendsController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FriendsService friendsService;

    @Autowired
    LikesService likesService;



    /**
     * 添加好友
     * @param record
     * @return
     */
    @RequestMapping("/addFriend")
    public Result<Boolean> addFriend(@RequestBody FriendsReq record){
        logger.info("addFriend，:{}",record);

        //判断是否存在好友关系: 作互为好友的判断，只要存在一条记录就表示已经是好友状态
        Friends friend = getFriendFromUsername(record.getFriendId(),record.getPlayerId());
        Friends friend1 = getFriendFromUsername(record.getPlayerId(),record.getFriendId());
        if(null == friend && friend1 ==null){
            friend = new Friends();
            friend.setAgree(0);
            friend.setCreateTime(new Date());
            friend.setFriendId(record.getFriendId());
            friend.setPlayerId(record.getPlayerId());
            friend.setUpdateTime(new Date());
            friend.setInvite("");
            if (StringUtils.isNotBlank(record.getInvite())){
                friend.setInvite(record.getInvite());
            }

            int applyCount = friendsService.isApplyCount(friend);
            if (applyCount > 0){
                return Result.result(true,"已经添加好友关系");
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
        Friends friend = getFriendFromUsername(friendId,playerId);
        if ("agree".equalsIgnoreCase(agree)){
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
    public Result<PageInfo<FriendsResp>> friendList(@RequestBody Page pageReq){
        logger.info("friendList，pageReq：{}",pageReq);
        PageInfo<FriendsResp> page = friendsService.friendList(pageReq);
        return new Result<>(Boolean.TRUE,"好友列表",page);
    }


    /**
     * 好友申请列表
     * @param pageReq
     * @return
     */
    @RequestMapping("/applyFriendList")
    public Result<PageInfo<FriendsResp>> applyFriendList(@RequestBody Page pageReq){
        logger.info("applyFriendList，pageReq：{}",pageReq);
        PageInfo page = friendsService.applyFriendList(pageReq);
        return new Result<>(Boolean.TRUE,"好友申请列表",page);
    }


    @RequestMapping("/to/friend/main")
    public Result<List<PlayerLikes>> toFriendMain(@RequestParam("playerId")String playerId){
        List<PlayerLikes> likes = likesService.getPlayerInvestLikes(playerId);

        return Result.result(true,"获取朋友首页数据成功", ReturnStatus.SUCCESS.getStatus(),likes);
    }



}