package com.dream.city.service.impl;

import com.dream.city.base.model.enu.GameSettingType;
import com.dream.city.base.model.entity.GameSetting;
import com.dream.city.domain.mapper.GameSettingMapper;
import com.dream.city.service.GameSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GameSettingServiceImpl implements GameSettingService {

    @Autowired
    private GameSettingMapper gameSettingMapper;


    @Override
    public boolean settingGameVioce(String playerId,boolean isOpen) {
        GameSetting gameSetting = gameSettingMapper.selectByType(GameSettingType.game.name());
        gameSetting.setPlayerId(playerId);
        gameSetting.setType(GameSettingType.game.name());
        gameSetting.setVal(String.valueOf(isOpen));
        gameSetting.setUpdateDate(new Date());
        return gameSettingMapper.updateByPrimaryKeySelective(gameSetting)>0?Boolean.TRUE:Boolean.FALSE;
    }

    @Override
    public boolean settingBgVioce(String playerId,boolean isOpen) {
        GameSetting gameSetting = gameSettingMapper.selectByType(GameSettingType.bg.name());
        gameSetting.setPlayerId(playerId);
        gameSetting.setType(GameSettingType.bg.name());
        gameSetting.setVal(String.valueOf(isOpen));
        gameSetting.setUpdateDate(new Date());
        return gameSettingMapper.updateByPrimaryKeySelective(gameSetting)>0?Boolean.TRUE:Boolean.FALSE;
    }
}
