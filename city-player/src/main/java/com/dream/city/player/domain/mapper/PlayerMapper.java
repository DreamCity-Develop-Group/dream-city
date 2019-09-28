package com.dream.city.player.domain.mapper;


import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.req.PageReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PlayerMapper {

    Integer deleteByPlayerId(String playerId);

    Integer insertSelective(Player record);

    Player getPlayerById(Player record);

    /**
     * 广场玩家列表
     * @param pageReq
     * @return
     */
    List<Map> getPlayers(PageReq pageReq);
    Integer getPlayersCount(PageReq pageReq);

    Integer updateByPlayerId(Player record);

    Player getPlayerByInvite(String invite);

    Player getPlayerByAccount(String account);
}