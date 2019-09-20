package com.dream.city.domain.mapper;

import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author wvv
 */
@Repository
public interface PlayerMapper {

    @Results(id = "BasePlayerResultMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "playerId", column = "player_id"),
            @Result(property = "playerName", column = "player_name"),
            @Result(property = "playerNick", column = "player_nick"),
            @Result(property = "playerPass", column = "player_pass"),
            @Result(property = "playerTradePass", column = "player_trade_pass"),
            @Result(property = "playerInvite", column = "player_invite"),
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


}
