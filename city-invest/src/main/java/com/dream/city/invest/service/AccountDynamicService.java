package com.dream.city.invest.service;

import com.dream.city.base.model.entity.AccountDynamic;

import java.util.List;

/**
 * 动账记录
 */
public interface AccountDynamicService {

    int insertSelective(AccountDynamic record);

    AccountDynamic selectByPrimaryKey(Integer dynId);

    List<AccountDynamic> getAccountDynamicList(AccountDynamic record);

}
