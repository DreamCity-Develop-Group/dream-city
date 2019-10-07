package com.dream.city.player.service.impl;

import com.dream.city.base.model.Page;
import com.dream.city.base.model.entity.Friends;
import com.dream.city.base.model.mapper.FriendsMapper;
import com.dream.city.player.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Wvv
 */
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
        return friendsMapper.agreeAddFriend(friend)>0?Boolean.TRUE:Boolean.FALSE;
    }

    @Override
    public Page friendList(Page pageReq) {
        Page<Map> page = new Page<>();
        page.setCondition(pageReq.getCondition());

        Integer count = friendsMapper.friendCount(pageReq);
        List<Map> friendList = friendsMapper.friendList(pageReq);
        page.setResult(friendList);
        page.setTotal( count== null?0:count);
        return page;
    }

    @Override
    public Page applyFriendList(Page pageReq) {
        Page<Map> page = new Page<>();
        page.setCondition(pageReq.getCondition());

        Integer count = friendsMapper.applyFriendCount(pageReq);
        List<Map> friendList = friendsMapper.applyFriendList(pageReq);
        page.setResult(friendList);
        page.setTotal( count== null?0:count);
        return page;
    }

    @Override
    public Friends selectByPlayerIdFriendId(Friends record) {
        return friendsMapper.selectByPlayerIdFriendId(record);
    }

    @Override
    public Integer getFriendAgree(Friends record) {
        return friendsMapper.getFriendAgree(record);
    }
}
