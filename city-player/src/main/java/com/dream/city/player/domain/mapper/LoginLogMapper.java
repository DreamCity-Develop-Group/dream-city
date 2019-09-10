package com.dream.city.player.domain.mapper;


import com.dream.city.player.domain.entity.LoginLog;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginLogMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(LoginLog record);

    LoginLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LoginLog record);

}