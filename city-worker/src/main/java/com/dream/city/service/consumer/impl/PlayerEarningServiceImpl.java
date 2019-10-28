package com.dream.city.service.consumer.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.EarnIncomeLog;
import com.dream.city.base.model.entity.PlayerEarning;
import com.dream.city.base.model.mapper.EarnIncomeLogMapper;
import com.dream.city.base.model.mapper.PlayerEarningMapper;
import com.dream.city.base.model.resp.PlayerEarningResp;
import com.dream.city.service.PlayerEarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Wvv
 */
public class PlayerEarningServiceImpl  implements PlayerEarningService {
    @Autowired
    PlayerEarningMapper playerEarningMapper;

    @Autowired
    EarnIncomeLogMapper earnIncomeLogMapper;

    @LcnTransaction
    @Transactional
    @Override
    public List<PlayerEarning> getPlayerEarnByPlayerId(String playerId)throws BusinessException {
        PlayerEarning record = new PlayerEarning();
        record.setEarnPlayerId(playerId);
        return playerEarningMapper.selectPlayerEarningList(record);
    }

    @LcnTransaction
    @Transactional
    @Override
    public PlayerEarningResp getPlayerEarnByPlayerId(String playerId, Integer investId)throws BusinessException {
        PlayerEarning record = new PlayerEarning();
        record.setEarnPlayerId(playerId);
        record.setEarnInvestId(investId);
        return playerEarningMapper.getPlayerEarning(record);
    }

    @LcnTransaction
    @Transactional
    @Override
    public void add(PlayerEarning earning)throws BusinessException {
        playerEarningMapper.insertSelective(earning);
    }

    @LcnTransaction
    @Transactional
    @Override
    public void update(PlayerEarning playerEarning)throws BusinessException {
        playerEarningMapper.updateByPrimaryKeySelective(playerEarning);
    }

    @LcnTransaction
    @Transactional
    @Override
    public void addEarnLog(EarnIncomeLog earnIncomeLog)throws BusinessException {
        earnIncomeLogMapper.add(earnIncomeLog);
    }
}
