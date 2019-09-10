package com.dream.city.player.domain.mapper;


import com.dream.city.player.domain.entity.GameSetting;
import org.springframework.stereotype.Repository;

@Repository
public interface GameSettingMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(GameSetting record);

    GameSetting selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GameSetting record);

    int updateByType(GameSetting record);

}