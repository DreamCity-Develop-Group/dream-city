package com.dream.city.base.model.enu;

/**
* 交易类型（充值:recharge,转账:transfer,提现:withdraw,购买mt:buy_mt,投资invest)
 * */
public enum TradeType {

    CHANGE_TRAN_PWD("CHANGE_TRAN_PWD","修改交易密码"),

    PLATFORM_CHANGE_AMOUNT("CHANGE_TRAN_PWD","管理后台修改账户额度"),

    INVEST("INVEST","USDT投资"),
    INVEST_EARNINGS("INVEST_EARNINGS","投资收益"),
    INVEST_TAX("INVEST_TAX","提取扣税"),

    BUY_MT("BUY_MT","购买MT"),
    LEVEL_EARN("LEVEL_EARN","印记分成"),

    RECHARGE("RECHARGE","充值"),

    TRANSFER("TRANSFER","转账"),
    TRANSFER_FROM("TRANSFER_FROM","转账转出"),
    TRANSFER_TO("TRANSFER_TO","转账转入"),

    WITHDRAW("WITHDRAW","提现"),

    RECEIVABLES("RECEIVABLES","平台账户进账");

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
