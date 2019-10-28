package com.dream.city.player.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Page;
import com.dream.city.base.model.entity.Friends;
import com.dream.city.base.model.mapper.FriendsMapper;
import com.dream.city.base.model.req.FriendsReq;
import com.dream.city.base.model.resp.FriendsResp;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.player.service.FriendsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Wvv
 */
@Service
public class FriendsServiceImpl implements FriendsService {

    @Autowired
    private FriendsMapper friendsMapper;


    @LcnTransaction
    @Transactional
    @Override
    public boolean addFriend(Friends friend) throws BusinessException {
        friend.setAgree(0);

        return friendsMapper.insertSelective(friend)>0?Boolean.TRUE:Boolean.FALSE;
    }

    @LcnTransaction
    @Transactional
    @Override
    public boolean agreeAddFriend(Friends friend) throws BusinessException {
        return friendsMapper.agreeAddFriend(friend)>0?Boolean.TRUE:Boolean.FALSE;
    }

    /*@Override
    public Page friendList(Page pageReq) {
        Page<Map> page = new Page<>();
        page.setCondition(pageReq.getCondition());

        Integer count = friendsMapper.friendCount(pageReq);
        List<Map> friendList = friendsMapper.friendList(pageReq);
        page.setResult(friendList);
        page.setTotal( count== null?0:count);
        return page;
    }*/

    @LcnTransaction
    @Transactional
    @Override
    public PageInfo<FriendsResp> friendList(Page pageReq)  throws BusinessException{// TODO
        FriendsReq friendsReq = DataUtils.toJavaObject(pageReq.getCondition(),FriendsReq.class);
        PageHelper.startPage(pageReq.getPageNum(),pageReq.getPageSize());
        List<FriendsResp> friendList = friendsMapper.friendList(friendsReq);
        return new PageInfo(friendList);
    }

    /*
    @LcnTransaction
    @Transactional
    @Override
    public Page applyFriendList(Page pageReq) throws BusinessException {
        Page<Map> page = new Page<>();
        page.setCondition(pageReq.getCondition());

        Integer count = friendsMapper.applyFriendCount(pageReq);
        List<Map> friendList = friendsMapper.applyFriendList(pageReq);
        page.setResult(friendList);
        page.setTotal( count== null?0:count);
        return page;
    }*/
    @LcnTransaction
    @Transactional
    @Override
    public PageInfo<FriendsResp> applyFriendList(Page pageReq) throws BusinessException {// TODO
        FriendsReq friendsReq = DataUtils.toJavaObject(pageReq.getCondition(),FriendsReq.class);
        PageHelper.startPage(pageReq.getPageNum(),pageReq.getPageSize());
        List<FriendsResp> friendList = friendsMapper.applyFriendList(friendsReq);
        return new PageInfo(friendList);
    }

    @LcnTransaction
    @Transactional
    @Override
    public Friends selectByPlayerIdFriendId(Friends record) throws BusinessException {
        return friendsMapper.selectByPlayerIdFriendId(record);
    }

    @LcnTransaction
    @Transactional
    @Override
    public Integer getFriendAgree(Friends record) throws BusinessException {
        return friendsMapper.getFriendAgree(record);
    }

    @LcnTransaction
    @Transactional
    @Override
    public int isApplyCount(Friends record) throws BusinessException {
        Integer count = friendsMapper.isApplyCount(record);
        return count == null?0:count;
    }
}
