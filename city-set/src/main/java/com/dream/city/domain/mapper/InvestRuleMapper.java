package com.dream.city.domain.mapper;


import com.dream.city.base.model.entity.InvestRule;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InvestRuleMapper {
    int deleteByPrimaryKey(Integer ruleId);

    int insert(InvestRule record);

    int insertSelective(InvestRule record);

    InvestRule selectByPrimaryKey(Integer ruleId);

    int updateByPrimaryKeySelective(InvestRule record);

    int updateByPrimaryKey(InvestRule record);
}