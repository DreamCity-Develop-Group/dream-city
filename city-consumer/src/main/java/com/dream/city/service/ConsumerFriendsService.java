package com.dream.city.service;

import com.dream.city.base.model.Page;
import com.dream.city.base.model.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "city-player")
@RequestMapping("/friends")
public interface ConsumerFriendsService {


    /**
     * 添加好友
     * @param playerId
     * @param friendId
     * @return
     */
    @RequestMapping("/addFriend")
    Result<Boolean> addFriend(@RequestParam("playerId") String playerId,
                              @RequestParam("friendId") String friendId);

    /**
     * 同意添加好友
     * @param playerId
     * @param friendId
     * @return
     */
    @RequestMapping("/agreeAddFriend")
    Result<Boolean> agreeAddFriend(@RequestParam("playerId") String playerId,
                           @RequestParam("friendId") String friendId,@RequestParam("agree") String agree);

    /**
     * 好友列表
     * @param pageReq
     * @return
     */
    @RequestMapping("/friendList")
    Result<PageInfo> friendList(@RequestBody Page pageReq);

    /**
     * 好友申请列表
     * @param pageReq
     * @return
     */
    @RequestMapping("/applyFriendList")
    Result<PageInfo> applyFriendList(@RequestBody Page pageReq);

}
