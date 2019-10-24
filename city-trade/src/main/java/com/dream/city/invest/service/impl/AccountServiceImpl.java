package com.dream.city.invest.service.impl;

import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.PlayerAccountLog;
import com.dream.city.base.model.mapper.AccountMapper;
import com.dream.city.base.model.mapper.PlayerAccountLogMapper;
import com.dream.city.base.model.req.PlayerAccountReq;
import com.dream.city.base.model.mapper.PlayerAccountMapper;
import com.dream.city.base.service.DictionaryService;
import com.dream.city.invest.service.AccountService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

//    @Value("${dreamcity.platform.account.accIds}")
//    String platformAccIds;
    @Autowired
    private PlayerAccountMapper playerAccountMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private PlayerAccountLogMapper playerAccountLogMapper;
    @Autowired
    DictionaryService dictionaryService;

    @Override
    @Transactional
    public int createPlayerAccount(PlayerAccount record) {
        Integer integer = accountMapper.insertSelective(record);
        return integer ==null?0:integer;
    }

    @Override
    public PlayerAccount getPlayerAccount(String playerId) {
        if (StringUtils.isBlank(playerId)){
            return null;
        }
        return playerAccountMapper.getPlayerAccount(playerId);
    }

    @Override
    public PlayerAccount getPlayerAccount(PlayerAccount record) {
        return accountMapper.getPlayerAccountSelective(record);
    }

    @Override
    public List<PlayerAccount> getPlayerAccountList(PlayerAccount record) {
        return accountMapper.getPlayerAccountList(record);
    }

    @Override
    @Transactional
    public int updatePlayerAccount(PlayerAccount record) {
        playerAccountMapper.updatePlayerAccount(record);
        return 1;
    }

    @Override
    @Transactional
    public int updatePlayerAccountById(PlayerAccount record) {
        Integer integer = accountMapper.updateByPrimaryKeySelective(record);
        return integer == null?0:integer;
    }


    @Override
    public List<PlayerAccount> getPlatformAccounts(PlayerAccountReq record) {
        if (record == null || (record != null && StringUtils.isBlank(record.getPlatformAccIds()))) {
            record.setPlatformAccIds(dictionaryService.getValByKey("platform.account.accIds"));
        }
        return accountMapper.getPlatformAccounts(record);
    }

    @Override
    public PlayerAccount getPlayerAccountByPlayerId(String playerId){
        return playerAccountMapper.getPlayerAccountByPlayerId(playerId);
    }

    @Override
    public int addAccountLog(PlayerAccountLog accountLog) {
        Integer integer = playerAccountLogMapper.insert(accountLog);
        return integer == null?0:integer;
    }

}
