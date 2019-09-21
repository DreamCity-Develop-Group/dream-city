package com.dream.city.service.impl;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.domain.mapper.PlayerAccountMapper;
import com.dream.city.domain.mapper.PlayerMapper;
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
        return  playerAccountMapper.getPlayerAccount(playerId);
    }


}
