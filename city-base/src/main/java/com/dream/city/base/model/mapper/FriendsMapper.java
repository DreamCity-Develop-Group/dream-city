package com.dream.city.base.model.mapper;


import com.dream.city.base.model.Page;
import com.dream.city.base.model.entity.Friends;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author WVV
 */
@Mapper
public interface FriendsMapper {

    int deleteByPrimaryKey(Long id);


    int insertSelective(Friends record);

    Friends selectByPrimaryKey(Long id);

    Friends selectByPlayerIdFriendId(Friends record);

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
    List<Map> friendList(Page page);
    Integer friendCount(Page page);

    /**
     * 申请列表
     * @param page
     * @return
     */
    List<Map> applyFriendList(Page page);
    Integer applyFriendCount(Page page);

    Integer getFriendAgree(Friends record);
}