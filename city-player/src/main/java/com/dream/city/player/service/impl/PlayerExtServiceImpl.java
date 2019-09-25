package com.dream.city.player.service.impl;

import com.dream.city.base.model.entity.PlayerExt;
import com.dream.city.player.domain.mapper.PlayerExtMapper;
import com.dream.city.player.service.PlayerExtService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerExtServiceImpl implements PlayerExtService {


    @Autowired
    PlayerExtMapper playerExtMapper;

    @Override
    public Integer deletePlayerExtById(Long id) {
        return playerExtMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public Integer insertPlayerExt(PlayerExt record) {
        return playerExtMapper.insertSelective(record);
    }

    @Override
    public PlayerExt getPlayerExtById(Long id) {
        return playerExtMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updatePlayerExtById(PlayerExt record) {
        return playerExtMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updatePlayerExtByPlayerId(PlayerExt record) {
        if (StringUtils.isBlank(record.getPlayerId())){
            return 0;
        }
        return playerExtMapper.updateByPlayerIdSelective(record);
    }
}
