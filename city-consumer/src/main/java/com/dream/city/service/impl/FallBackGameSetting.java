package com.dream.city.service.impl;

import com.dream.city.service.ConsumerGameSettingService;

public class FallBackGameSetting implements ConsumerGameSettingService {
    @Override
    public String settingGameVioce(String playerId, Boolean isOpen) {
        return null;
    }

    @Override
    public String settingBgVioce(String playerId, Boolean isOpen) {
        return null;
    }
}
