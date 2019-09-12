package com.dream.city.player.domain.mapper;


import com.dream.city.base.model.Page;
import com.dream.city.player.domain.entity.Friends;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface FriendsMapper {

    int deleteByPrimaryKey(Long id);


    int insertSelective(Friends record);

    Friends selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Friends record);

    List<Friends> friendList(Page<Friends> page);
}