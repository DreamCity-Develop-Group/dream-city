package com.dream.city.player.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.PlayerExt;
import com.dream.city.base.model.mapper.PlayerExtMapper;
import com.dream.city.player.service.PlayerExtService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerExtServiceImpl implements PlayerExtService {


    @Autowired
    PlayerExtMapper playerExtMapper;

    @LcnTransaction
    @Transactional
    @Override
    public Integer deletePlayerExtById(Long id) throws BusinessException {
        return playerExtMapper.deleteByPrimaryKey(id);
    }

    @LcnTransaction
    @Transactional
    @Override
    public Integer insertPlayerExt(PlayerExt record) throws BusinessException {
        return playerExtMapper.insertSelective(record);
    }

    @LcnTransaction
    @Transactional
    @Override
    public PlayerExt getPlayerExtById(Long id) throws BusinessException {
        return playerExtMapper.selectByPrimaryKey(id);
    }

    @LcnTransaction
    @Transactional
    @Override
    public Integer updatePlayerExtById(PlayerExt record) throws BusinessException {
        return playerExtMapper.updateByPrimaryKeySelective(record);
    }

    @LcnTransaction
    @Transactional
    @Override
    public Integer updatePlayerExtByPlayerId(PlayerExt record) throws BusinessException {
        if (StringUtils.isBlank(record.getPlayerId())){
            return 0;
        }
        return playerExtMapper.updateByPlayerIdSelective(record);
    }
}
