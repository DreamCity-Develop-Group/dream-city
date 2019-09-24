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

    /**
     * 当天点赞次数
     * @param record 点赞人di，点赞物业id
     * @return
     */
    int playerLikesCountToday(PlayerLikesLog record);
}