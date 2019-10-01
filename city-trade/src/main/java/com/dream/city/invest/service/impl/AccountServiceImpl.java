package com.dream.city.invest.service.impl;

import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.req.PlayerAccountReq;
import com.dream.city.invest.domain.mapper.PlayerAccountMapper;
import com.dream.city.invest.service.AccountService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Value("${dreamcity.platform.account.accIds}")
    String platformAccIds;
    @Autowired
    PlayerAccountMapper accountMapper;



    @Override
    @Transactional
    public int createPlayerAccount(PlayerAccount record) {
        Integer integer = accountMapper.insertSelective(record);
        return integer ==null?0:integer;
    }

    @Override
    public PlayerAccount getPlayerAccount(PlayerAccount record) {
        if (record.getAccId() == null && StringUtils.isBlank(record.getAccPlayerId())){
            return null;
        }
        PlayerAccountReq accountReq = new PlayerAccountReq();
        accountReq.setAccId(record.getAccId());
        accountReq.setAccPlayerId(record.getAccPlayerId());
        accountReq.setPlatformAccIds(platformAccIds);
        return accountMapper.getPlayerAccount(accountReq);
    }

    @Override
    public List<PlayerAccount> getPlayerAccountList(PlayerAccount record) {
        return accountMapper.getPlayerAccountList(record);
    }

    @Override
    @Transactional
    public int updatePlayerAccount(PlayerAccount record) {
        Integer integer = accountMapper.updateByPlayerId(record);
        return integer ==null?0:integer;
    }


    @Override
    public List<PlayerAccount> getPlatformAccounts(PlayerAccountReq record) {
        if (record == null || (record != null && StringUtils.isBlank(record.getPlatformAccIds()))) {
            record.setPlatformAccIds(platformAccIds);
        }
        return accountMapper.getPlatformAccounts(record);
    }

    @Override
    public PlayerAccount getPlayerAccountByPlayerId(String playerId){
        return accountMapper.getPlayerAccountByPlayerId(playerId);
    }


}
