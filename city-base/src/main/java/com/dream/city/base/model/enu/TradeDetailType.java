package com.dream.city.base.model.enu;

/**
 * 交易明细类型
 *
 */
public enum TradeDetailType {

    RECHARGE("RECHARGE","充值"),

    USDT_INVEST_FREEZE("USDT_INVEST_FREEZE","usdt投资冻结"),
    USDT_INVEST_VERIFY("USDT_INVEST_VERIFY","usdt投资审核通过扣款"),
    USDT_EARNINGS("USDT_EARNINGS","usdt投资收益"),

    MT_INVES_FREEZE("MT_INVES_FREEZE","mt投资冻结"),
    MT_INVEST_PERSONAL_TAX("MT_INVEST_PERSONAL_TAX","mt投资个人税金"),
    MT_INVEST_ENTERPRISE_TAX("MT_INVEST_ENTERPRISE_TAX","mt投资企业税金"),
    MT_EARNINGS("MT_EARNINGS","mt投资收益"),
    BUY_MT_FREEZE("BUY_MT_FREEZE","购买mt冻结"),

    PERSONAL_TAX("PERSONAL_TAX","个人所得税"),
    ENTERPRISE_TAX("ENTERPRISE_TAX","企业所得税"),

    TRANSFER_FREEZE("TRANSFER_FREEZE","转账冻结"),
    TRANSFER_VERIFY("TRANSFER_VERIFY","转账审核通过扣款"),
    TRANSFER_TAX("TRANSFER_TAX","转账所得税"),

    WITHDRAW_FREEZE("WITHDRAW_FREEZE","提现冻结"),
    WITHDRAW_VERIFY("WITHDRAW_VERIFY","提现审核通过扣款"),
    WITHDRAW_TAX("WITHDRAW_TAX","提现所得税"),

    RECEIVABLES_TRANSFER_TAX("RECEIVABLES_TRANSFER_TAX","平台账户转账所得税进账"),
    RECEIVABLES_WITHDRAW_TAX("RECEIVABLES_WITHDRAW_TAX","平台账户提现所得税进账"),
    RECEIVABLES_INVEST_USDT("RECEIVABLES_INVEST_USDT","平台账户USDT投资进账"),
    RECEIVABLES_INVEST_TAX("RECEIVABLES_INVEST_TAX","平台账户USDT投资税金进账");

    private String code;
    private String desc;

    TradeDetailType(String code, String desc){
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
