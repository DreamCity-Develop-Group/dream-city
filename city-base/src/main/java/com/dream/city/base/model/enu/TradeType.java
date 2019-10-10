package com.dream.city.base.model.enu;

/**
* 交易类型（充值:recharge,转账:transfer,提现:withdraw,购买mt:buy_mt,投资invest)
 * */
public enum TradeType {

    INVEST("INVEST","USDT投资"),
    BUY_MT("BUY_MT","购买MT"),
    INVEST_EARNINGS("INVEST_EARNINGS","投资收益"),
    TRANSFER("TRANSFER","转账"),
    RECHARGE("RECHARGE","充值"),
    WITHDRAW("WITHDRAW","提现");

    private String code;
    private String desc;

    TradeType(String code, String desc){
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
