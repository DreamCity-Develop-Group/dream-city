package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.EarnFalldownLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * ss
 */
@Mapper
public interface EarnFalldownLogMapper {
    int deleteByPrimaryKey(Integer fallId);

    int insert(EarnFalldownLog record);

    int insertSelective(EarnFalldownLog record);

    EarnFalldownLog selectByPrimaryKey(Integer fallId);

    int updateByPrimaryKeySelective(EarnFalldownLog record);

    int updateByPrimaryKey(EarnFalldownLog record);
}