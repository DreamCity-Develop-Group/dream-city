package com.dream.city.player.service;

import com.dream.city.base.model.Page;
import com.dream.city.player.domain.entity.Friends;

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
     * @param playerId
     * @return
     */
    Page friendList(String playerId);

    /**
     * 申请列表
     * @param playerId
     * @return
     */
    Page applyFriendList(String playerId);

}
