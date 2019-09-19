package com.dream.city.player.domain.mapper;

import com.dream.city.base.model.entity.PlayerLikesLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlayerLikesLogMapper {

    int deleteByPrimaryKey(Integer likeId);

    int insert(PlayerLikesLog record);

    int insertSelective(PlayerLikesLog record);

    PlayerLikesLog selectByPrimaryKey(Integer likeId);

    int updateByPrimaryKeySelective(PlayerLikesLog record);

    int updateByPrimaryKey(PlayerLikesLog record);
}