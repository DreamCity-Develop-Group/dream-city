package com.dream.city.player.domain.mapper;


import com.dream.city.base.model.entity.PlayerLikes;
import com.dream.city.player.domain.req.PlayerLikesReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlayerLikesMapper {

    Integer deleteByPrimaryKey(Integer likedId);


    Integer insertSelective(PlayerLikesReq record);


    PlayerLikes selectByPrimaryKey(Integer likedId);


    Integer updateByPrimaryKeySelective(PlayerLikesReq record);


    /**
     * 玩家点赞总数
     * @param record
     * @return
     */
    Integer playerLikesCount(PlayerLikesReq record);

    /**
     * 点赞项目
     * @param record
     * @return
     */
    List<PlayerLikes> playerLikesList(PlayerLikesReq record);
}