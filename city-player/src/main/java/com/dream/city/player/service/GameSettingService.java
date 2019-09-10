package com.dream.city.player.service;

/**
 * 游戏设置
 */
public interface GameSettingService {

    /**
     * 游戏音效
     * @return
     */
    boolean settingGameVioce(String playerId,boolean isOpen);


    /**
     * 背景音效
     * @param isOpen
     * @return
     */
    boolean settingBgVioce(String playerId,boolean isOpen);

}
