package com.dream.city.service.impl;

import com.dream.city.base.model.mapper.PlayerLikesMapper;
import com.dream.city.service.PlayerLikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Wvv
 */
@Service
public class PlayerLikesServiceImpl  implements PlayerLikesService {
    @Autowired
    PlayerLikesMapper playerLikesMapper;

    @Override
    public int getLikesById(String orderPayerId){

        return playerLikesMapper.getLikesById(orderPayerId);
    }

    @Override
    public int getLikesGetByPlayerId(String orderPayerId) {
        return playerLikesMapper.getLikesGetByPlayerId(orderPayerId);
    }
}
