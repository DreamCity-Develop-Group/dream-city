package com.dream.city.base.model.enu;

/**
 * 资金动态(进账in,出账out)
 * */
public enum AmountDynType {

    IN("IN","进账"),
    OUT("OUT","出账");

    private String code;
    private String desc;

    AmountDynType(String code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
