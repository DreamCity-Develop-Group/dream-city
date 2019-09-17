package com.dream.city.player.domain.mapper;


import com.dream.city.player.domain.entity.PlayerLikes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlayerLikesMapper {

    Integer deleteByPrimaryKey(Integer likedId);


    Integer insertSelective(PlayerLikes record);


    PlayerLikes selectByPrimaryKey(Integer likedId);


    Integer updateByPrimaryKeySelective(PlayerLikes record);


    /**
     * 玩家点赞总数
     * @param record
     * @return
     */
    Integer playerLikesCount(PlayerLikes record);

    /**
     * 点赞项目
     * @param record
     * @return
     */
    List<PlayerLikes> playerLikesList(PlayerLikes record);
}