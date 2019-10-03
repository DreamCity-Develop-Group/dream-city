package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.PlayerEarning;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 玩家提现收入
 */
@Mapper
public interface PlayerEarningMapper {

    Integer deleteByPrimaryKey(Integer earnId);

    Integer insertSelective(PlayerEarning record);

    PlayerEarning selectByPrimaryKey(Integer earnId);

    PlayerEarning getPlayerEarningByPlayerId(PlayerEarning record);

    List<PlayerEarning> selectPlayerEarningList(PlayerEarning record);

    Integer updateByPrimaryKeySelective(PlayerEarning record);

}