package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.EarnIncomeLog;
import com.dream.city.base.model.entity.PlayerEarning;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 玩家 收益记录
 * @author Wvv
 */
@Mapper
public interface EarnIncomeLogMapper {

    @Results(id = "BaseEarningResultMap", value = {
            @Result(property = "inLogId", column = "in_log_id", id = true),
            @Result(property = "inInvestId", column = "in_invest_id"),
            @Result(property = "inPlayerId", column = "in_player_id"),
            @Result(property = "inAmount", column = "in_amount"),
            @Result(property = "createTime", column = "create_time"),
    })

    @Insert("insert into earn_income_log values(#{inLogId},#{inInvestId},#{inPlayerId},#{inAmount},#{createTime})")
    void add(EarnIncomeLog earnIncomeLog);
    /**
     *查找当前玩家当前项目的收益
     *
     * @param playerId
     * @param investId
     * @return
     */
    @Select("select * from player_earning where 1=1 and earn_player_id=#{playerId} and earn_invest_id=#{investId}")
    //@ResultMap("BaseEarningResultMap1")
    PlayerEarning getPlayerEarning(@Param("playerId") String playerId, @Param("investId") Integer investId);

}