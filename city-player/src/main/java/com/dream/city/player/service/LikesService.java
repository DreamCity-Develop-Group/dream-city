package com.dream.city.player.service;


import com.dream.city.base.model.Page;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerLikes;
import com.dream.city.base.model.req.PlayerLikesReq;
import com.dream.city.base.model.resp.PlayerLikesResp;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface LikesService {


    /**
     * 点赞
     * @param record
     * @return
     */
    int playerLike(PlayerLikesReq record);

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
    PageInfo<PlayerLikesResp> playerLikesList(Page record);


    /**
     * 当天点赞次数
     * 好友
     * @param record
     * @return
     */
    int playerLikesCountToday(PlayerLikesReq record);

    /**
     * 当天点赞次数
     * 投资
     * @param record
     * @return
     */
    int investLikesCountToday(PlayerLikesReq record);

    /**
     *
     * @param playerId
     * @return
     */
    List<PlayerLikes> getPlayerInvestLikes(String playerId);

    int getLikeCount(Integer likedId);

    void savePlayerLikesLog(PlayerLikesReq playerLikes);

    Result like(String from, String to);
}