package com.dream.city.service.impl;

import com.dream.city.base.model.entity.PlayerEarning;
import com.dream.city.base.model.mapper.PlayerEarningMapper;
import com.dream.city.service.PlayerEarningService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Wvv
 */
public class PlayerEarningServiceImpl  implements PlayerEarningService {
    @Autowired
    PlayerEarningMapper playerEarningMapper;
    @Override
    public PlayerEarning getPlayerEarnByPlayerId(String playerId) {
        return playerEarningMapper.getPlayerEarningByPlayerId(playerId);
    }
}
