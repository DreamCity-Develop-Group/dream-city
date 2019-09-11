package com.dream.city.domain.mapper;


import com.dream.city.domain.entity.AuthCode;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthCodeMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(AuthCode record);

    AuthCode selectByPrimaryKey(Long id);

    String selectByPhone(String phone);

    int updateByPrimaryKeySelective(AuthCode record);

}