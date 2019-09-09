package com.dream.city.player.domain.mapper;


import com.dream.city.player.domain.entity.PlayerGrade;

public interface PlayerGradeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlayerGrade record);

    int insertSelective(PlayerGrade record);

    PlayerGrade selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlayerGrade record);

    int updateByPrimaryKey(PlayerGrade record);
}