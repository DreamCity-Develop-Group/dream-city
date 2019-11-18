package com.dream.city.invest.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.PlayerEarning;
import com.dream.city.base.model.mapper.PlayerEarningMapper;
import com.dream.city.base.model.resp.PlayerEarningResp;
import com.dream.city.invest.service.EarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class EarningServiceImpl implements EarningService {

    @Autowired
    private PlayerEarningMapper earningMapper;

    @LcnTransaction
    @Transactional
    @Override
    public int deleteEarningById(Integer earnId) throws BusinessException {
        Integer i = earningMapper.deleteByPrimaryKey(earnId);
        return i == null? 0: i;
    }

    @LcnTransaction
    @Transactional
    @Override
    public int insertEarning(PlayerEarning record) throws BusinessException {
        Integer i = earningMapper.insertSelective(record);
        return i == null? 0: i;
    }

    @LcnTransaction
    @Transactional
    @Override
    public PlayerEarning getEarning(Integer earnId) throws BusinessException {
        return earningMapper.selectByPrimaryKey(earnId);
    }

    @LcnTransaction
    @Transactional
    @Override
    public PlayerEarningResp getPlayerEarningByPlayerId(String playerId, Integer inType)  throws BusinessException{
        PlayerEarning record = new PlayerEarning();
        record.setEarnPlayerId(playerId);
        record.setInType(inType);
        return earningMapper.getPlayerEarning(record);
    }

    @LcnTransaction
    @Transactional
    @Override
    public List<PlayerEarning> getEarningList(PlayerEarning record) throws BusinessException {
        return earningMapper.selectPlayerEarningList(record);
    }

    @LcnTransaction
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int updateEarningById(PlayerEarning record) throws BusinessException {
        record.setUpdateTime(new Date());
        Integer i = earningMapper.updateByPrimaryKeySelective(record);
        return i == null? 0: i;
    }

    @LcnTransaction
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PlayerEarning getEarningInvestByPlayerId(String playerId, String inType) {
        PlayerEarning earning = earningMapper.getEarningInvestByPlayerId(playerId,inType);
        return earning;
    }
}
