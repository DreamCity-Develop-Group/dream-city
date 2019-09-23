package com.dream.city.base.model.enu;

/**
* 交易类型（投资usdt，投资mt，转账transfer，充值recharge，提现withdraw）
 * */
public enum TradeType {

    usdt("投资usdt"),
    mt("投资mt"),
    transfer("转账"),
    recharge("充值"),
    withdraw("提现");

    private String desc;

    TradeType(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
