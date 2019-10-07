package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.PlayerGameSetting;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface GameSettingMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(PlayerGameSetting record);

    PlayerGameSetting selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlayerGameSetting record);

    int updateByType(PlayerGameSetting record);

    PlayerGameSetting selectByType(String type);

}