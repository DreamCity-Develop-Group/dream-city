package com.dream.city.player.service;


import com.dream.city.player.domain.entity.PlayerLikes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

public interface LikesService {


    /**
     * 点赞
     * @param record
     * @return
     */
    int playerLike(PlayerLikes record);

    /**
     * 取消点赞
     * @param record
     * @return
     */
    int cancelLike(PlayerLikes record);

    /**
     * 玩家点赞总数
     * @param record
     * @return
     */
    int playerLikesCount(PlayerLikes record);

    /**
     * 点赞项目
     * @param record
     * @return
     */
    List<PlayerLikes> playerLikesList(PlayerLikes record);

}