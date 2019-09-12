package com.dream.city.player.service;

import com.dream.city.base.model.Page;
import com.dream.city.player.domain.entity.Friends;

import java.util.List;

public interface FriendsService {

    /**
     * 添加好友
     * @param friend
     * @return
     */
    boolean addFriend(Friends friend);

    /**
     * 同意添加好友
     * @param id
     * @return
     */
    boolean agreeAddFriend(Long id);

    /**
     * 好友列表
     * @param playerId
     * @return
     */
    Page friendList(String playerId);

}
