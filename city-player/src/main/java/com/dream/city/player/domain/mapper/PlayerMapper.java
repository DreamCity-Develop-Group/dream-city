package com.dream.city.player.domain.mapper;


import com.dream.city.base.model.req.PageReq;
import com.dream.city.player.domain.entity.Player;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlayerMapper {

    int deleteByPlayerId(String playerId);

    int insertSelective(Player record);

    Player getPlayerById(String playerId);

    /**
     * 广场玩家列表
     * @param pageReq
     * @return
     */
    List<Player> getPlayers(PageReq pageReq);

    int updateByPlayerId(Player record);

}