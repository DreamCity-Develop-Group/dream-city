package com.dream.city.player.service.impl;

import com.dream.city.base.model.enu.GameSettingType;
import com.dream.city.player.domain.entity.GameSetting;
import com.dream.city.player.domain.mapper.GameSettingMapper;
import com.dream.city.player.service.GameSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GameSettingServiceImpl implements GameSettingService {

    @Autowired
    private GameSettingMapper gameSettingMapper;


    @Override
    public boolean settingGameVioce(String playerId,boolean isOpen) {
        GameSetting record = new GameSetting();
        record.setPlayerId(playerId);
        record.setType(GameSettingType.game.name());
        record.setVal(String.valueOf(isOpen));
        record.setUpdateDate(new Date());
        return gameSettingMapper.updateByType(record)>0?Boolean.TRUE:Boolean.FALSE;
    }

    @Override
    public boolean settingBgVioce(String playerId,boolean isOpen) {
        GameSetting record = new GameSetting();
        record.setPlayerId(playerId);
        record.setType(GameSettingType.bg.name());
        record.setVal(String.valueOf(isOpen));
        record.setUpdateDate(new Date());
        return gameSettingMapper.updateByType(record)>0?Boolean.TRUE:Boolean.FALSE;
    }
}
