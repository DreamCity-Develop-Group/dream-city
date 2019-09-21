package com.dream.city.invest.service.impl;


import com.dream.city.base.model.entity.AccountDynamic;
import com.dream.city.invest.domain.mapper.AccountDynamicMapper;
import com.dream.city.invest.service.AccountDynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountDynamicServiceImpl implements AccountDynamicService {


    @Autowired
    AccountDynamicMapper dynamicMapper;



    @Override
    public int insertSelective(AccountDynamic record) {
        Integer integer = dynamicMapper.insertSelective(record);
        return integer ==null?0:integer;
    }

    @Override
    public AccountDynamic selectByPrimaryKey(Integer dynId) {
        return dynamicMapper.selectByPrimaryKey(dynId);
    }

    @Override
    public List<AccountDynamic> getAccountDynamicList(AccountDynamic record) {
        return dynamicMapper.getAccountDynamicList(record);
    }
}
