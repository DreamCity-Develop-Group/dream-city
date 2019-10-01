package com.dream.city.domain.mapper;

import com.dream.city.base.model.entity.InvestAllow;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * @author wvv
 */
@Repository
public interface InvestAllowMapper {

    @Results(id = "BasePlayerResultMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "playerId", column = "player_id"),
            @Result(property = "allowed", column = "allowed"),
            @Result(property = "amount", column = "amount"),
            @Result(property = "createTime", column = "createtime"),
    })
    @Select({"select * from `invest_allow` where 1=1 and player_id = #{playerId}"})
    Player getPlayer(String playerId);



    /**
     *  玩家的资金账户
     * @param playerId
     * @return
     */
    @Select("select * from `invest_allow` where 1=1 and  player_id = #{playerId}")
    InvestAllow getInvestAllowByPlayerId(String playerId);

    @Insert("insert into `invest_allow`(id,player_id,allowed,amount,create_time)values(#{id},#{playerId},#{allowed},#{amount},#{createTime}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(InvestAllow allow);



}
