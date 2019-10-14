package com.dream.city.service.impl;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.PlayerAccountLog;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.model.mapper.PlayerAccountLogMapper;
import com.dream.city.base.model.mapper.PlayerAccountMapper;
import com.dream.city.base.model.mapper.PlayerMapper;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.PlayerAccountService;
import com.dream.city.service.PlayerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Wvv
 */
@Service
public class PlayerAccountServiceImpl implements PlayerAccountService {
    @Autowired
    private PlayerAccountMapper playerAccountMapper;

    @Autowired
    private PlayerAccountLogMapper playerAccountLogMapper;

    @Autowired
    PlayerService playerService;

    @Override
    public BigDecimal getPlayerAccountUSDTAvailble(String playerId) {
        PlayerAccount account = playerAccountMapper.getPlayerAccount(playerId);
        return account == null?new BigDecimal(0.00):account.getAccUsdtAvailable();
    }

    @Override
    public BigDecimal getPlayerAccountMTAvailble(String playerId) {
        PlayerAccount account = playerAccountMapper.getPlayerAccount(playerId);
        return account == null?new BigDecimal(0.00):account.getAccMtAvailable();
    }

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

    @Override
    public PlayerAccount getPlayerAccount(String playerId){
        PlayerAccount account = playerAccountMapper.getAccountByPlayerId(playerId);
        return account;
    }

    @Override
    public void createAccount(String playerId,String address){
         playerAccountMapper.createAccount(playerId,address);
    }

    @Override
    public void updatePlayerLevel(String playerId,Integer level){
        playerService.updatePlayerLevel(playerId,level);
    }

    @Override
    public Player getPlayerByPlayerId(String playerId) {
        return playerService.getPlayer(playerId);
    }

    @Override
    public Result lockUstdAmount(String playerId, BigDecimal amount) {
        PlayerAccount playerAccount = playerAccountMapper.getPlayerAccount(playerId);
        //减可用额度
        playerAccount.setAccUsdtAvailable(playerAccount.getAccUsdtAvailable().subtract(amount));
        //增加冻结额度
        playerAccount.setAccUsdtFreeze(playerAccount.getAccUsdtFreeze().add(amount));

        int num = playerAccountMapper.updatePlayerAccount(playerAccount);
        return Result.result(num>0);
    }

    @Override
    public void subtractAmount(BigDecimal orderPayAmount, String playerId) {
        playerAccountMapper.subtractAmount(orderPayAmount,playerId);
    }

    @Override
    public int updatePlayerAccount(PlayerAccount account) {
        return playerAccountMapper.updatePlayerAccount(account);
    }

    @Override
    public void updatePlayerAccounts(List<PlayerAccount> accounts) {
        playerAccountMapper.updatePlayerAccounts(accounts);
    }

    @Override
    public void addAccountLog(PlayerAccountLog accountLog) {
        playerAccountLogMapper.insert(accountLog);
    }


}
