package com.dream.city.player.service.impl;

import com.dream.city.base.model.Page;
import com.dream.city.player.domain.entity.Friends;
import com.dream.city.player.domain.mapper.FriendsMapper;
import com.dream.city.player.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FriendsServiceImpl implements FriendsService {

    @Autowired
    private FriendsMapper friendsMapper;

    @Override
    public boolean addFriend(Friends friend) {
        friend.setAgree(0);
        return friendsMapper.insertSelective(friend)>0?Boolean.TRUE:Boolean.FALSE;
    }

    @Override
    public boolean agreeAddFriend(Friends friend) {
        friend.setAgree(1);
        return friendsMapper.agreeAddFriend(friend)>0?Boolean.TRUE:Boolean.FALSE;
    }

    @Override
    public Page friendList(String playerId) {
        Page<Map> page = new Page<>();
        page.setCondition(playerId);

        Integer count = friendsMapper.friendCount(playerId);
        List<Map> friendList = friendsMapper.friendList(page);
        page.setResult(friendList);
        page.setTotalCount( count== null?0:count);
        return page;
    }

    @Override
    public Page applyFriendList(String playerId) {
        Page<Map> page = new Page<>();
        page.setCondition(playerId);

        Integer count = friendsMapper.applyFriendCount(playerId);
        List<Map> friendList = friendsMapper.applyFriendList(page);
        page.setResult(friendList);
        page.setTotalCount( count== null?0:count);
        return page;
    }
}
