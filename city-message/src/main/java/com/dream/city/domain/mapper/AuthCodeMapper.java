package com.dream.city.domain.mapper;


import com.dream.city.base.model.entity.AuthCode;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthCodeMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(AuthCode record);

    AuthCode selectByPrimaryKey(Long id);

    String selectByPhone(String phone);

    AuthCode getCodeByPhone(String phone);

    int updateByPrimaryKeySelective(AuthCode record);

}