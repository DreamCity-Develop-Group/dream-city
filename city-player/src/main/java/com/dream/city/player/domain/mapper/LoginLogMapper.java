package com.dream.city.player.domain.mapper;


import com.dream.city.player.domain.entity.LoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface LoginLogMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(LoginLog record);

    LoginLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LoginLog record);

}