package com.dream.city.base.model.enu;

/**
* 交易明细类型
 * （充值:recharge,usdt投资收益:usdt_earnings,mt投资收益:mt_earnings,
 * usdt投资税金:usdt_invest_tax,mt投资税金:mt_invest_tax,个人所得税:personal_tax,企业所得税:enterprise_tax,
 * 转账冻结:transfer_freeze,提现冻结:withdraw_freeze,购买mt冻结:buy_mt_freeze,usdt投资冻结:usdt_invest_freeze,
 * mt投资冻结:mt_inves_freeze,转账审核通过扣款:transfer_verify,提现审核通过扣款:withdraw_verify,usdt投资审核通过扣款:usdt_invest_verify
 * */
public enum TradeDetailType {

    RECHARGE("RECHARGE","充值"),
    USDT_EARNINGS("USDT_EARNINGS","usdt投资收益"),
    MT_EARNINGS("MT_EARNINGS","mt投资收益"),
    USDT_INVEST_TAX("USDT_INVEST_TAX","usdt投资税金"),
    MT_INVEST_TAX("MT_INVEST_TAX","mt投资税金"),
    PERSONAL_TAX("PERSONAL_TAX","个人所得税"),
    ENTERPRISE_TAX("ENTERPRISE_TAX","企业所得税"),
    TRANSFER_FREEZE("TRANSFER_FREEZE","转账冻结"),
    WITHDRAW_FREEZE("WITHDRAW_FREEZE","提现冻结"),
    BUY_MT_FREEZE("BUY_MT_FREEZE","购买mt冻结"),
    USDT_INVEST_FREEZE("USDT_INVEST_FREEZE","usdt投资冻结"),
    MT_INVES_FREEZE("MT_INVES_FREEZE","mt投资冻结"),
    TRANSFER_VERIFY("TRANSFER_VERIFY","转账审核通过扣款"),
    WITHDRAW_VERIFY("WITHDRAW_VERIFY","提现审核通过扣款"),
    USDT_INVEST_VERIFY("USDT_INVEST_VERIFY","usdt投资审核通过扣款");

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
