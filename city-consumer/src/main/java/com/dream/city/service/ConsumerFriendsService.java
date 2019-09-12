package com.dream.city.service;

import com.dream.city.base.model.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "city-player")
public interface ConsumerFriendsService {


    /**
     * 添加好友
     * @param jsonReq
     * @return
     */
    @RequestMapping("/friends/addFriend")
    boolean addFriend(@RequestParam("json")String jsonReq);

    /**
     * 同意添加好友
     * @param id
     * @return
     */
    @RequestMapping("/friends/agreeAddFriend")
    boolean agreeAddFriend(@RequestParam("id")Long id);

    /**
     * 好友列表
     * @param playerId
     * @return
     */
    @RequestMapping("/friends/friendList")
    Page friendList(@RequestParam("playerId") String playerId);



}
