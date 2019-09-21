package com.dream.city.invest.domain.mapper;


import com.dream.city.base.model.entity.AccountDynamic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountDynamicMapper {

    Integer deleteByPrimaryKey(Integer dynId);

    Integer insertSelective(AccountDynamic record);

    AccountDynamic selectByPrimaryKey(Integer dynId);

    List<AccountDynamic> getAccountDynamicList(AccountDynamic record);

    Integer updateByPrimaryKeySelective(AccountDynamic record);

}