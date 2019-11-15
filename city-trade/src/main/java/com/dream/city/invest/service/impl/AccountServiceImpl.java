package com.dream.city.invest.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.PlayerAccountLog;
import com.dream.city.base.model.mapper.AccountMapper;
import com.dream.city.base.model.mapper.PlayerAccountLogMapper;
import com.dream.city.base.model.mapper.PlayerAccountMapper;
import com.dream.city.base.model.req.PlayerAccountReq;
import com.dream.city.base.model.resp.PlayerAccountResp;
import com.dream.city.invest.service.AccountService;
import com.dream.city.invest.service.DictionaryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @LcnTransaction
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int createPlayerAccount(PlayerAccount record) throws BusinessException {
        Integer integer = accountMapper.insertSelective(record);
        return integer == null ? 0 : integer;
    }

    @LcnTransaction
    @Transactional
    @Override
    public PlayerAccount getPlayerAccount(String playerId) throws BusinessException {
        if (StringUtils.isBlank(playerId)) {
            return null;
        }
        return playerAccountMapper.getPlayerAccount(playerId);
    }

    @LcnTransaction
    @Transactional
    @Override
    public PlayerAccountResp getPlayerAccount(PlayerAccountReq record) throws BusinessException {
        String id = dictionaryService.getValByKey("platform.account.accIds");
        String[] ids = id.split(",");
        record.setPlatformAccIds(ids);

        return accountMapper.getPlayerAccountSelective(record);
    }

    @LcnTransaction
    @Transactional
    @Override
    public List<PlayerAccountResp> getPlayerAccountList(PlayerAccountReq record) throws BusinessException {
        String id = dictionaryService.getValByKey("platform.account.accIds");
        String[] ids = id.split(",");
        record.setPlatformAccIds(ids);

        return accountMapper.getPlayerAccountList(record);
    }

    @LcnTransaction
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int updatePlayerAccount(PlayerAccount record) throws BusinessException {
        playerAccountMapper.updatePlayerAccount(record);
        return 1;
    }

    @LcnTransaction
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int updatePlayerAccountById(PlayerAccount record) throws BusinessException {
        Integer integer = accountMapper.updateByPrimaryKeySelective(record);
        return integer == null ? 0 : integer;
    }


    @LcnTransaction
    @Transactional
    @Override
    public List<PlayerAccount> getPlatformAccounts(PlayerAccountReq record) throws BusinessException {
        if (record == null || (record != null && record.getPlatformAccIds().length == 0)) {
            String id = dictionaryService.getValByKey("platform.account.accIds");
            String[] ids = id.split(",");
            record.setPlatformAccIds(ids);
            //record.setAccAddr(dictionaryService.getValByKey("platform.account.accIds"));
        }
        return accountMapper.getPlatformAccounts(record);
    }

    @LcnTransaction
    @Transactional
    @Override
    public PlayerAccount getPlayerAccountByPlayerId(String playerId) throws BusinessException {
        return playerAccountMapper.getPlayerAccountByPlayerId(playerId);
    }

    @LcnTransaction
    @Transactional
    @Override
    public PlayerAccount getPlayerAccountByAddr(String addr) throws BusinessException {
        return playerAccountMapper.getPlayerAccountByAddr(addr);
    }

    @LcnTransaction
    @Transactional
    @Override
    public int addAccountLog(PlayerAccountLog accountLog) throws BusinessException {
        Integer integer = playerAccountLogMapper.insert(accountLog);
        return integer == null ? 0 : integer;
    }

}
