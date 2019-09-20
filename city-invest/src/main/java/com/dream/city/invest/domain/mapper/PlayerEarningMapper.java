package com.dream.city.invest.domain.mapper;


import com.dream.city.base.model.entity.PlayerEarning;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlayerEarningMapper {
    int deleteByPrimaryKey(Integer earnId);

    int insert(PlayerEarning record);

    int insertSelective(PlayerEarning record);

    PlayerEarning selectByPrimaryKey(Integer earnId);

    int updateByPrimaryKeySelective(PlayerEarning record);

    int updateByPrimaryKey(PlayerEarning record);
}