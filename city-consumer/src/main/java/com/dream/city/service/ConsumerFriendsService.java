package com.dream.city.service;

import com.dream.city.base.model.Page;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.req.FriendsReq;
import com.dream.city.base.model.resp.FriendsResp;
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
     * @param record
     * @return
     */
    @RequestMapping("/addFriend")
    Result<Boolean> addFriend(@RequestBody FriendsReq record);

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
    Result<PageInfo<FriendsResp>> friendList(@RequestBody Page pageReq);

    /**
     * 好友申请列表
     * @param pageReq
     * @return
     */
    @RequestMapping("/applyFriendList")
    Result<PageInfo<FriendsResp>> applyFriendList(@RequestBody Page pageReq);

    /**
     * 获取好友主页投资点赞数据
     * @param playerId
     */
    @RequestMapping("/to/friend/main")
    Result getInvestLikes(@RequestParam("playerId") String playerId);
}
