package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.AuthCode;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Wvv
 */
@Mapper
public interface AuthCodeMapper {

    int deleteByPrimaryKey(Long id);

    int insertSelective(AuthCode record);

    AuthCode selectByPrimaryKey(Long id);

    String selectByPhone(String phone);

    AuthCode getCodeByPhone(String phone);

    int updateByPrimaryKeySelective(AuthCode record);
}