package com.dream.city.player.domain.mapper;


import com.dream.city.base.model.entity.AccountDynamic;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountDynamicMapper {

    int deleteByPrimaryKey(Integer dynId);

    int insert(AccountDynamic record);

    int insertSelective(AccountDynamic record);

    AccountDynamic selectByPrimaryKey(Integer dynId);

    int updateByPrimaryKeySelective(AccountDynamic record);

    int updateByPrimaryKey(AccountDynamic record);
}