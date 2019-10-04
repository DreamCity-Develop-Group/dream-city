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

    @Insert("insert into earn_income_log values(#{earnId},#{inInvestId},#{inPlayerId},#{inAmount})")
    void add(EarnIncomeLog earnIncomeLog);
    /**
     *查找当前玩家当前项目的收益
     *
     * @param playerId
     * @param investId
     * @return
     */
    @Select("select * from player_earning where 1=1 and earn_player_id=#{playerId} and earn_invest_id=#{investId}")
    @ResultMap("BaseResultMap")
    PlayerEarning getPlayerEarning(@Param("playerId") String playerId, @Param("investId") Integer investId);

}