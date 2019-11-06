package com.dream.city.service.impl;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Notice;
import com.dream.city.service.consumer.ConsumerGameSettingService;

import java.util.List;

public class FallBackGameSetting implements ConsumerGameSettingService {
    @Override
    public Result settingGameVioce(String playerId, Boolean isOpen) {
        return null;
    }

    @Override
    public Result settingBgVioce(String playerId, Boolean isOpen) {
        return null;
    }

    @Override
    public List<Notice> getGameNoties() {
        return null;
    }
}
