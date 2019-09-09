package com.dream.city.player.domain.mapper;


import com.dream.city.domain.entity.PlayerExt;

public interface PlayerExtMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlayerExt record);

    int insertSelective(PlayerExt record);

    PlayerExt selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlayerExt record);

    int updateByPrimaryKey(PlayerExt record);
}