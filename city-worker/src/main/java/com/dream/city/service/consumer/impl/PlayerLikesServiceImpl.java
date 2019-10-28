package com.dream.city.service.consumer.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.mapper.PlayerLikesMapper;
import com.dream.city.service.PlayerLikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Wvv
 */
@Service
public class PlayerLikesServiceImpl  implements PlayerLikesService {
    @Autowired
    PlayerLikesMapper playerLikesMapper;


    @LcnTransaction
    @Transactional
    @Override
    public int getLikesById(String orderPayerId)throws BusinessException {

        return playerLikesMapper.getLikesById(orderPayerId);
    }

    @LcnTransaction
    @Transactional
    @Override
    public int getLikesGetByPlayerId(String orderPayerId) throws BusinessException{
        return playerLikesMapper.getLikesGetByPlayerId(orderPayerId);
    }
}
