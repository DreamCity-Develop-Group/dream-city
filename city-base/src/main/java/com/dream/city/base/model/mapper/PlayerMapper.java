package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.req.PageReq;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface PlayerMapper {

    Integer deleteByPlayerId(String playerId);

    Integer insertSelective(Player record);

    Player getPlayerById(Player record);

    List<Map> getPlayers(PageReq pageReq);
    Integer getPlayersCount(PageReq pageReq);

    /**
     * 广场玩家列表
     * @param pageReq
     * @return
     */
    List<Map> getSquareFriends(PageReq pageReq);
    Integer getSquareFriendsCount(PageReq pageReq);

    Integer updateByPlayerId(Player record);

    Player getPlayerByInvite(String invite);

    Player getPlayerByAccount(String account);

    @Results(id = "BasePlayerResultMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "playerId", column = "player_id"),
            @Result(property = "playerName", column = "player_name"),
            @Result(property = "playerNick", column = "player_nick"),
            @Result(property = "playerPass", column = "player_pass"),
            @Result(property = "playerTradePass", column = "player_trade_pass"),
            @Result(property = "playerInvite", column = "player_invite"),
            @Result(property = "playerLevel", column = "player_invite"),
            @Result(property = "isValid", column = "is_valid"),
            @Result(property = "createTime", column = "createtime"),
            @Result(property = "updateTime", column = "updatetime")
    })
    @Select({"select * from `city_player` where 1=1 and player_id = #{playerId}"})
    Player getPlayer(String playerId);



    /**
     *  玩家的资金账户
     * @param playerId
     * @return
     */
    @Select("select * from player_account where 1=1 and  acc_player_id = #{playerId}")
    PlayerAccount getPlayerAccount(String playerId);

    @Update("update city_player set player_level=#{level} where player_id=#{playerId}")
    void updatePlayerLevel(String playerId,Integer level);
}