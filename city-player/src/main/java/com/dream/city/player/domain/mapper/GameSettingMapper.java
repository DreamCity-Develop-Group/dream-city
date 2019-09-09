package com.dream.city.player.domain.mapper;


import com.dream.city.domain.entity.GameSetting;

public interface GameSettingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GameSetting record);

    int insertSelective(GameSetting record);

    GameSetting selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GameSetting record);

    int updateByPrimaryKey(GameSetting record);
}