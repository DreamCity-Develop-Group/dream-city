package com.dream.city.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.PlayerAccountLog;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.model.mapper.PlayerAccountLogMapper;
import com.dream.city.base.model.mapper.PlayerAccountMapper;
import com.dream.city.service.PlayerAccountService;
import com.dream.city.service.PlayerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Wvv
 */
@Service
public class PlayerAccountServiceImpl implements PlayerAccountService  {
    @Autowired
    private PlayerAccountMapper playerAccountMapper;

    @Autowired
    private PlayerAccountLogMapper playerAccountLogMapper;

    @Autowired
    PlayerService playerService;

    @LcnTransaction
    @Transactional
    @Override
    public BigDecimal getPlayerAccountUSDTAvailble(String playerId)  throws BusinessException {
        PlayerAccount account = playerAccountMapper.getPlayerAccount(playerId);
        return account == null?new BigDecimal(0.00):account.getAccUsdtAvailable();
    }

    @LcnTransaction
    @Transactional
    @Override
    public BigDecimal getPlayerAccountMTAvailble(String playerId)  throws BusinessException{
        PlayerAccount account = playerAccountMapper.getPlayerAccount(playerId);
        return account == null?new BigDecimal(0.00):account.getAccMtAvailable();
    }

    @LcnTransaction
    @Transactional
    @Override
    public Result checkPayPass(String playerId, String payPass) {
        Player player = playerService.getPlayer(playerId);
        if (StringUtils.isBlank(player.getPlayerTradePass())){
            return Result.result(false,"支付密码尚未设置，请设置密码", ReturnStatus.NOTSET_PASS.getStatus());
        }
        if (player.getPlayerTradePass().equals(payPass)){
            return Result.result(true,"支付密码验证通过",ReturnStatus.SUCCESS.getStatus());
        }else {
            return Result.result(false,"支付密码不正确",ReturnStatus.ERROR_PASS.getStatus());
        }

    }

    @LcnTransaction
    @Transactional
    @Override
    public PlayerAccount getPlayerAccount(String playerId) throws BusinessException{
        PlayerAccount account = playerAccountMapper.getAccountByPlayerId(playerId);
        return account;
    }

    @LcnTransaction
    @Transactional
    @Override
    public void createAccount(String playerId,String address) throws BusinessException{
         playerAccountMapper.createAccount(playerId,address);
    }

    @LcnTransaction
    @Transactional
    @Override
    public void updatePlayerLevel(String playerId,Integer level) throws BusinessException{
        playerService.updatePlayerLevel(playerId,level);
    }

    @LcnTransaction
    @Transactional
    @Override
    public Player getPlayerByPlayerId(String playerId) throws BusinessException {
        return playerService.getPlayer(playerId);
    }

    @LcnTransaction
    @Transactional
    @Override
    public Result lockUstdAmount(String playerId, BigDecimal amount) throws BusinessException {
        PlayerAccount playerAccount = playerAccountMapper.getPlayerAccount(playerId);
        //减可用额度
        playerAccount.setAccUsdtAvailable(playerAccount.getAccUsdtAvailable().subtract(amount));
        //增加冻结额度
        playerAccount.setAccUsdtFreeze(playerAccount.getAccUsdtFreeze().add(amount));

        int num = playerAccountMapper.updatePlayerAccount(playerAccount);
        if (num>0){
            PlayerAccountLog accountLog = new PlayerAccountLog();
            accountLog.setDesc("锁定玩家额度");
            accountLog.setType(2);
            accountLog.setAmountUsdt(amount);
            accountLog.setPlayerId(playerId);
            accountLog.setAccId(playerAccount.getAccId());
            accountLog.setCreateTime(new Date());
            accountLog.setAddress(playerAccount.getAccAddr());
            accountLog.setAmountMt(new BigDecimal(0));
            playerAccountLogMapper.insert(accountLog);
        }
        return Result.result(num>0);
    }

    @LcnTransaction
    @Transactional
    @Override
    public Result unlockUstdAmount(String playerId, BigDecimal amount) throws BusinessException{
        PlayerAccount playerAccount = playerAccountMapper.getPlayerAccount(playerId);
        //减可用额度
        playerAccount.setAccUsdt(playerAccount.getAccUsdt().subtract(amount));
        //增加冻结额度
        playerAccount.setAccUsdtFreeze(playerAccount.getAccUsdtFreeze().subtract(amount));

        int num = playerAccountMapper.updatePlayerAccount(playerAccount);
        if (num>0){
            PlayerAccountLog accountLog = new PlayerAccountLog();
            accountLog.setDesc("释放锁定玩家额度");
            accountLog.setType(2);
            accountLog.setAmountUsdt(amount);
            accountLog.setPlayerId(playerId);
            accountLog.setAccId(playerAccount.getAccId());
            accountLog.setCreateTime(new Date());
            accountLog.setAddress(playerAccount.getAccAddr());
            accountLog.setAmountMt(new BigDecimal(0));
            playerAccountLogMapper.insert(accountLog);
        }
        return Result.result(num>0);
    }

    @LcnTransaction
    @Transactional
    @Override
    public void subtractAmount(BigDecimal orderPayAmount, String playerId) throws BusinessException {
        playerAccountMapper.subtractAmount(orderPayAmount,playerId);
    }

    @LcnTransaction
    @Transactional
    @Override
    public int updatePlayerAccount(PlayerAccount account) throws BusinessException {
        return playerAccountMapper.updatePlayerAccount(account);
    }

    @LcnTransaction
    @Transactional
    @Override
    public void updatePlayerAccounts(List<PlayerAccount> accounts)  throws BusinessException{
        playerAccountMapper.updatePlayerAccounts(accounts);
    }

    @LcnTransaction
    @Transactional
    @Override
    public void addAccountLog(PlayerAccountLog accountLog)  throws BusinessException{
        playerAccountLogMapper.insert(accountLog);
    }


}
