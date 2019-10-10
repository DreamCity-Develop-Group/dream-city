package com.dream.city.base.model.enu;

public enum GameSettingType {

    BG("背景音效"),
    GAME("游戏音效");

    // 成员变量
    private String desc;

    GameSettingType(String desc){
        this.desc = desc;
    }


    public String getDesc() {
        return desc;
    }
}
