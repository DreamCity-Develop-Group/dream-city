package com.dream.city.service.consumer.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.PlayerAccountLog;
import com.dream.city.base.model.mapper.AccountMapper;
import com.dream.city.base.model.mapper.PlayerAccountLogMapper;
import com.dream.city.base.model.mapper.PlayerAccountMapper;
import com.dream.city.service.PlayerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Wvv
 * 
 */
@Service
public class PlayerAccountServiceImpl implements PlayerAccountService {
    @Autowired
    PlayerAccountMapper playerAccountMapper;
    @Autowired
    AccountMapper accountMapper;

    @Autowired
    PlayerAccountLogMapper playerAccountLogMapper;

    @LcnTransaction
    @Transactional
    @Override
    public void addAccountLog(PlayerAccountLog accountLog)throws BusinessException {
        playerAccountLogMapper.insert(accountLog);
    }

    @LcnTransaction
    @Transactional
    @Override
    public void updateProfitToAccount(PlayerAccount account)throws BusinessException {
        accountMapper.updateByPrimaryKeySelective(account);
    }

    @LcnTransaction
    @Transactional
    @Override
    public PlayerAccount getPlayerAccount(String playerId)throws BusinessException {
        return playerAccountMapper.getPlayerAccount(playerId);
    }
}
