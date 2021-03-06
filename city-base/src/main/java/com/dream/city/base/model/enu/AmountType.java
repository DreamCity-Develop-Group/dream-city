package com.dream.city.base.model.enu;

/**
 * 投资usdt，投资mt
 * */
public enum AmountType {

    USDT("usdt"),
    MT("mt");

    private String desc;

    AmountType(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
