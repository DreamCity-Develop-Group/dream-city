package com.dream.city.domain.mapper;


import com.dream.city.base.model.entity.PlayerEarning;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 玩家提现规则
 */
@Mapper
public interface PlayerEarningMapper {

    Integer deleteByPrimaryKey(Integer earnId);

    Integer insertSelective(PlayerEarning record);

    PlayerEarning selectByPrimaryKey(Integer earnId);

    List<PlayerEarning> selectPlayerEarningList(PlayerEarning record);

    Integer updateByPrimaryKeySelective(PlayerEarning record);

}