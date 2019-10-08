package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.PlayerGameSetting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PlayerGameSettingMapper {

    int deleteByPrimaryKey(Long id);

    int insertSelective(PlayerGameSetting record);

    PlayerGameSetting selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlayerGameSetting record);

    int updateByType(PlayerGameSetting record);

    List<PlayerGameSetting> getGameSettingList(PlayerGameSetting record);

    PlayerGameSetting selectByType(@Param("type") String type);

}