package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.GameSetting;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface GameSettingMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(GameSetting record);

    GameSetting selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GameSetting record);

    int updateByType(GameSetting record);

    GameSetting selectByType(String type);

}