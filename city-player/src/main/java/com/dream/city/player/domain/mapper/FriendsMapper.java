package com.dream.city.player.domain.mapper;


import com.dream.city.base.model.Page;
import com.dream.city.base.model.req.PageReq;
import com.dream.city.player.domain.entity.Friends;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FriendsMapper {

    int deleteByPrimaryKey(Long id);


    int insertSelective(Friends record);

    Friends selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Friends record);

    /**
     * 同意添加好友
     * @param record
     * @return
     */
    int agreeAddFriend(Friends record);

    /**
     * 好友列表
     * @param page
     * @return
     */
    List<Map> friendList(PageReq page);
    Integer friendCount(String playerId);


    /**
     * 申请列表
     * @param page
     * @return
     */
    List<Map> applyFriendList(PageReq page);
    Integer applyFriendCount(String playerId);
}