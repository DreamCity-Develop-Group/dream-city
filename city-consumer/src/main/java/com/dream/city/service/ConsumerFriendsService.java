package com.dream.city.service;

import com.dream.city.base.model.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "city-player")
public interface ConsumerFriendsService {


    /**
     * 添加好友
     * @param playerId
     * @param friendId
     * @return
     */
    @RequestMapping("/friends/addFriend")
    boolean addFriend(@RequestParam("playerId") String playerId,
                      @RequestParam("friendId") String friendId);

    /**
     * 同意添加好友
     * @param playerId
     * @param friendId
     * @return
     */
    @RequestMapping("/friends/agreeAddFriend")
    boolean agreeAddFriend(@RequestParam("playerId") String playerId,
                           @RequestParam("friendId") String friendId);

    /**
     * 好友列表
     * @param playerId
     * @return
     */
    @RequestMapping("/friends/friendList")
    Page friendList(@RequestParam("playerId") String playerId);

    /**
     * 好友申请列表
     * @param playerId
     * @return
     */
    @RequestMapping("/friends/applyFriendList")
    Page applyFriendList(@RequestParam("playerId") String playerId);

}
