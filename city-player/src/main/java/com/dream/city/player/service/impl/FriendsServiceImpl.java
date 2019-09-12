package com.dream.city.player.service.impl;

import com.dream.city.base.model.Page;
import com.dream.city.player.domain.entity.Friends;
import com.dream.city.player.domain.mapper.FriendsMapper;
import com.dream.city.player.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FriendsServiceImpl implements FriendsService {

    @Autowired
    private FriendsMapper friendsMapper;

    @Override
    public boolean addFriend(Friends friend) {
        friend.setCreateTime(new Date());
        friend.setAgree(0);
        return friendsMapper.insertSelective(friend)>0?Boolean.TRUE:Boolean.FALSE;
    }

    @Override
    public boolean agreeAddFriend(Long id) {
        Friends friend = new Friends();
        friend.setUpdateTime(new Date());
        friend.setAgree(1);
        return friendsMapper.updateByPrimaryKeySelective(friend)>0?Boolean.TRUE:Boolean.FALSE;
    }

    @Override
    public Page friendList(String playerId) {
        Page<Friends> page = new Page<>();
        page.setCondition(playerId);

        List<Friends> friends = friendsMapper.friendList(page);
        page.setResult(friends);
        return page;
    }
}
