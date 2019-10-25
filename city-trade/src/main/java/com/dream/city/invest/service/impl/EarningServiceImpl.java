package com.dream.city.invest.service.impl;

import com.dream.city.base.model.entity.PlayerEarning;
import com.dream.city.base.model.mapper.PlayerEarningMapper;
import com.dream.city.base.model.resp.PlayerEarningResp;
import com.dream.city.invest.service.EarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EarningServiceImpl implements EarningService {

    @Autowired
    private PlayerEarningMapper earningMapper;

    @Override
    public int deleteEarningById(Integer earnId) {
        Integer i = earningMapper.deleteByPrimaryKey(earnId);
        return i == null? 0: i;
    }

    @Override
    public int insertEarning(PlayerEarning record) {
        Integer i = earningMapper.insertSelective(record);
        return i == null? 0: i;
    }

    @Override
    public PlayerEarning getEarning(Integer earnId) {
        return earningMapper.selectByPrimaryKey(earnId);
    }

    @Override
    public PlayerEarningResp getPlayerEarningByPlayerId(String playerId, Integer investId) {
        PlayerEarning record = new PlayerEarning();
        record.setEarnPlayerId(playerId);
        record.setEarnInvestId(investId);
        return earningMapper.getPlayerEarning(record);
    }

    @Override
    public List<PlayerEarning> getEarningList(PlayerEarning record) {
        return earningMapper.selectPlayerEarningList(record);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int updateEarningById(PlayerEarning record) {
        Integer i = earningMapper.updateByPrimaryKeySelective(record);
        return i == null? 0: i;
    }
}
