package com.dream.city.service.impl;

import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.PlayerAccountLog;
import com.dream.city.base.model.mapper.PlayerAccountLogMapper;
import com.dream.city.base.model.mapper.PlayerAccountMapper;
import com.dream.city.service.PlayerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Wvv
 * 
 */
@Service
public class PlayerAccountServiceImpl implements PlayerAccountService {
    @Autowired
    PlayerAccountMapper playerAccountMapper;

    @Autowired
    PlayerAccountLogMapper playerAccountLogMapper;

    @Override
    public void addAccountLog(PlayerAccountLog accountLog) {
        playerAccountLogMapper.insert(accountLog);
    }

    @Override
    public void updateProfitToAccount(PlayerAccount account) {
        playerAccountMapper.updateByPrimaryKeySelective(account);
    }

    @Override
    public PlayerAccount getPlayerAccount(String playerId) {
        return playerAccountMapper.getPlayerAccount(playerId);
    }
}
