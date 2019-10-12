package com.dream.city.service.impl;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.mapper.PlayerAccountMapper;
import com.dream.city.base.model.mapper.PlayerMapper;
import com.dream.city.service.PlayerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Wvv
 */
@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerMapper playerMapper;




    @Override
    public Player getPlayerByPlayerId(String playerId) {
        return playerMapper.getPlayer(playerId);
    }

    @Override
    public Player getPlayer(String playerId) {
        return playerMapper.getPlayer(playerId);
    }

    @Override
    public void updatePlayerLevel(String playerId, Integer level) {
        playerMapper.updatePlayerLevel(playerId,level);
    }


}