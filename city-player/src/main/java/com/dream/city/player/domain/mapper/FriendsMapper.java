package com.dream.city.player.domain.mapper;


import com.dream.city.base.model.Page;
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

    int agreeAddFriend(Friends record);

    Integer friendCount(String playerId);
    List<Map> friendList(Page<Map> page);
}