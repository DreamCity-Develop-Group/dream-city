package com.dream.city.service.impl;

import com.dream.city.base.model.entity.InvestAllow;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.RelationTree;
import com.dream.city.base.model.mapper.InvestAllowMapper;
import com.dream.city.base.model.mapper.PlayerAccountMapper;
import com.dream.city.service.InvestAllowService;
import io.swagger.models.Model;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author Wvv
 */
@Service
public class InvestServiceImpl implements InvestAllowService {
    @Value("${platform.account}")
    private String platformAccount;

    @Autowired
    InvestAllowMapper investMapper;

    @Autowired
    PlayerAccountMapper accountMapper;

    @Override
    public InvestAllow getInvestAllowByPlayerId(String playerId) {

        return investMapper.getInvestAllowByPlayerId(playerId);
    }

    @Override
    public boolean addInvestAllow(String playerId, BigDecimal amount) {
        InvestAllow allow = new InvestAllow();
        allow.setAmount(amount);
        allow.setPlayerId(playerId);
        allow.setId(0);
        allow.setAllowed("1");
        allow.setCreateTime(new Timestamp(System.currentTimeMillis()));

        investMapper.insert(allow);
        return true;
    }

    @Override
    public BigDecimal getRateDirection() {

        return new BigDecimal(0.35);
    }

    @Override
    public BigDecimal getRateInterpolation() {
        return new BigDecimal(0.05);
    }

    /**
     * TODO 分配最终收益到平台账户
     *
     * @param amount
     */
    @Override
    public void allowcationUSDTToPlatform(BigDecimal amount) {
        PlayerAccount accountPlatform = accountMapper.getPlayerAccount(platformAccount);
        accountPlatform.setAccUsdt(accountPlatform.getAccUsdt().add(amount));
        accountPlatform.setAccUsdt(accountPlatform.getAccUsdt().add(amount));
        accountMapper.updatePlayerAccount(accountPlatform);
    }

    /**
     * TODO 分配比例收益到玩家
     *
     * @param amount
     * @param relationTree
     */
    @Override
    public void allowcationUSDTToPlayer(BigDecimal amount, RelationTree relationTree) {
        PlayerAccount accountPlayer = accountMapper.getPlayerAccount(relationTree.getTreePlayerId());
        accountPlayer.setAccUsdt(accountPlayer.getAccUsdt().add(amount));
                accountPlayer.setAccUsdtAvailable(accountPlayer.getAccUsdtAvailable().add(amount));

                accountMapper.updatePlayerAccount(accountPlayer);
                }
                }
