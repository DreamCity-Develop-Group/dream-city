package com.dream.city.base.model.enu;

/**
* 交易类型（投资usdt，投资mt，转账transfer，充值recharge，提现withdraw）
 * */
public enum TradeAmountType {

    USDT_INVEST("USDT_INVEST","usdt投资"),
    USDT_INVEST_TAX("USDT_INVEST_TAX","usdt投资mt税金"),
    MT_INVEST("MT_INVEST","mt投资"),
    USDT_EARNINGS("USDT_EARNINGS","usdt投资收益"),
    TRANSFER("TRANSFER","转账"),
    RECHARGE("RECHARGE","充值"),
    WITHDRAW("WITHDRAW","提现");

    private String code;
    private String desc;

    TradeAmountType(String code, String desc){
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
