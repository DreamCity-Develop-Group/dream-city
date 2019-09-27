package com.dream.city.base.model.enu;

/**
* 交易类型（投资usdt，投资mt，转账transfer，充值recharge，提现withdraw）
 * */
public enum TradeAmountType {

    USDT_INVEST("usdt_invest","投资usdt"),
    USDT_DTINVEST_TAX("mt_invest_tax","usdt投资mt税金"),
    MT_INVEST("mt_invest","投资mt"),
    USDT_EARNINGS("usdt_earnings","usdt投资收益"),
    TRANSFER("transfer","转账"),
    RECHARGE("recharge","充值"),
    WITHDRAW("withdraw","提现");

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
