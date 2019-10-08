package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.PlayerEarning;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 玩家提现收入
 * @author Wvv
 */
@Mapper
public interface PlayerEarningMapper {

    Integer deleteByPrimaryKey(Integer earnId);

    Integer insertSelective(PlayerEarning record);

    PlayerEarning selectByPrimaryKey(Integer earnId);

    PlayerEarning getPlayerEarning(PlayerEarning record);

    List<PlayerEarning> selectPlayerEarningList(PlayerEarning record);

    Integer updateByPrimaryKeySelective(PlayerEarning record);


    /**
     *查找当前玩家当前项目的收益
     *
     * @param playerId
     * @param investId
     * @return
     */
    @Select("select * from player_earning where 1=1 and earn_player_id=#{playerId} and earn_invest_id=#{investId}")
    PlayerEarning getPlayerEarning(@Param("playerId") String playerId, @Param("investId")Integer investId);

}