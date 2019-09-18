package com.dream.city.player.service;


import com.dream.city.player.domain.entity.PlayerLikes;
import com.dream.city.player.domain.req.PlayerLikesReq;

import java.util.List;

public interface LikesService {


    /**
     * 点赞
     * @param record
     * @return
     */
    int playerLike(PlayerLikesReq  record);

    /**
     * 取消点赞
     * @param record
     * @return
     */
    int cancelLike(PlayerLikesReq  record);

    /**
     * 玩家点赞总数
     * @param record
     * @return
     */
    int playerLikesCount(PlayerLikesReq  record);

    /**
     * 点赞项目
     * @param record
     * @return
     */
    List<PlayerLikes> playerLikesList(PlayerLikesReq  record);

}