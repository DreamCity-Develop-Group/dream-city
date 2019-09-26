package com.dream.city.base.model.enu;

/**
* 交易类型（投资usdt，投资mt，转账transfer，充值recharge，提现withdraw）
 * */
public enum TradeType {

    USDTINVEST("usdt_invest","投资usdt"),
    USDTINVESTTAX("usdt_invest_tax","usdt投资税金"),
    MTINVEST("mt_invest","投资mt"),
    USDTEARNINGS("usdt_earnings","usdt投资收益"),
    TRANSFER("transfer","转账"),
    RECHARGE("recharge","充值"),
    WITHDRAW("withdraw","提现");

    private String code;
    private String desc;

    TradeType(String code,String desc){
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
