package com.dream.city.service.impl;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.mapper.PlayerAccountMapper;
import com.dream.city.base.model.mapper.PlayerMapper;
import com.dream.city.service.PlayerAccountService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Wvv
 */
@Service
public class PlayerAccountServiceImpl implements PlayerAccountService {
    @Autowired
    private PlayerAccountMapper playerAccountMapper;
    @Autowired
    private PlayerMapper playerMapper;

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
        Player player = playerMapper.getPlayer(playerId);
        if (StringUtils.isBlank(player.getPlayerTradePass())){
            return new Result(false,"支付密码尚未设置，请设置密码",500);
        }
        if (player.getPlayerTradePass().equals(payPass)){
            return new Result(true,"支付密码验证通过",200);
        }else {
            return new Result(false,"支付密码不正确",500);
        }

    }

    @Override
    public PlayerAccount getPlayerAccount(String playerId){
        //return  playerAccountMapper.getPlayerAccount(playerId);
        //return playerAccountMapper.getPlayerAccountByPlayerId(playerId);
        //PlayerAccount account = playerAccountMapper.getPlayerAccount(playerId);
        PlayerAccount account = playerAccountMapper.getAccountByPlayerId(playerId);
        //return playerAccountMapper.findPlayerAccount(1);
        return account;
    }

    @Override
    public void createAccount(String playerId,String address){
         playerAccountMapper.createAccount(playerId,address);
    }

    @Override
    public void updatePlayerLevel(String playerId,Integer level){
        playerMapper.updatePlayerLevel(playerId,level);
    }

    @Override
    public Player getPlayerByPlayerId(String playerId) {
        return playerMapper.getPlayer(playerId);
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


}
