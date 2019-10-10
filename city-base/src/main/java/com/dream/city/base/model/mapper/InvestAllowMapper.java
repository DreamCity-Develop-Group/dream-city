package com.dream.city.base.model.mapper;

import com.dream.city.base.model.entity.InvestAllow;
import com.dream.city.base.model.entity.Player;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author wvv
 */
@Repository
@Mapper
public interface InvestAllowMapper {

    @Results(id = "BasePlayerResultMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "playerId", column = "player_id"),
            @Result(property = "allowed", column = "allowed"),
            @Result(property = "amount", column = "amount"),
            @Result(property = "createTime", column = "createtime"),
    })
    @Select({"select * from `invest_allow` where 1=1 and player_id = #{playerId}"})
    InvestAllow getInvestAllow(String playerId);





    /**
     *  玩家的资金账户
     * @param playerId
     * @return
     */
    @Select("select * from `invest_allow` where 1=1 and  player_id = #{playerId}")
    @ResultMap("BasePlayerResultMap")
    InvestAllow getInvestAllowByPlayerId(String playerId);

    @Insert("insert into `invest_allow`(id,player_id,allowed,amount,create_time)values(#{id},#{playerId},#{allowed},#{amount},#{createTime}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(InvestAllow allow);



}
