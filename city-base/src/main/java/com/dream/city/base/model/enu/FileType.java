package com.dream.city.base.model.enu;

public enum FileType {

    PLAYER_IMG("player_img","玩家头像"),
    PROPERTY("property","物业图片");

    private String code;
    private String desc;

    FileType(String code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getCode() {
        return code;
    }
}
