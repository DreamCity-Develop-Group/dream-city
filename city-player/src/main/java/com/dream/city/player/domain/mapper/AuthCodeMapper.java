package com.dream.city.player.domain.mapper;


import com.dream.city.domain.entity.AuthCode;

public interface AuthCodeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuthCode record);

    int insertSelective(AuthCode record);

    AuthCode selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthCode record);

    int updateByPrimaryKey(AuthCode record);
}