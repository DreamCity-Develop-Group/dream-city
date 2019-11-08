package com.dream.city.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.mapper.PlayerMapper;
import com.dream.city.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Wvv
 */
@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerMapper playerMapper;




    @LcnTransaction
    @Transactional
    @Override
    public Player getPlayerByPlayerId(String playerId) throws BusinessException {
        return playerMapper.getPlayer(playerId);
    }

    @LcnTransaction
    @Transactional
    @Override
    public Player getPlayer(String playerId) throws BusinessException {
        return playerMapper.getPlayer(playerId);
    }

    @LcnTransaction
    @Transactional
    @Override
    public void updatePlayerLevel(String playerId, Integer level) throws BusinessException {
        playerMapper.updatePlayerLevel(playerId,level);
    }


}
