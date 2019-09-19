package com.dream.city.player.service;

import com.dream.city.base.model.Page;
import com.dream.city.base.model.req.PageReq;
import com.dream.city.base.model.entity.Friends;

/**
 * 好友
 */
public interface FriendsService {

    /**
     * 添加好友
     * @param friend
     * @return
     */
    boolean addFriend(Friends friend);

    /**
     * 同意添加好友
     * @param friend
     * @return
     */
    boolean agreeAddFriend(Friends friend);

    /**
     * 好友列表
     * @param pageReq
     * @return
     */
    Page friendList(PageReq pageReq);

    /**
     * 申请列表
     * @param pageReq
     * @return
     */
    Page applyFriendList(PageReq pageReq);

}
