package com.dream.city.base.model.enu;

/**
 * 资金动态(进账in,出账out)
 * */
public enum AmountDynType {

    in("进账"),
    out("出账");

    private String desc;

    AmountDynType(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
