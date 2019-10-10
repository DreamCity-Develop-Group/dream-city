package com.dream.city.base.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class TradeDetail implements Serializable {

        /**  */
        private Integer id;

        /**  */
        private Integer tradeId;

        /** 审核id */
        private Integer verifyId;

        /** 订单id */
        private Integer orderId;

        /** 交易人 */
        private String playerId;

        /**
         * 交易明细类型（充值:recharge,预约投资:invest_subscribe,usdt投资收益:usdt_earnings,mt投资收益:mt_earnings,
         * usdt投资税金:usdt_invest_tax,mt投资税金:mt_invest_tax,个人所得税:personal_tax,企业所得税:enterprise_tax,
         * 转账冻结:transfer_freeze,提现冻结:withdraw_freeze,购买mt冻结:buy_mt_freeze,usdt投资冻结:usdt_invest_freeze,mt投资冻结:mt_inves_freeze,
         * 转账审核通过扣款:transfer_verify,提现审核通过扣款:withdraw_verify,usdt投资审核通过扣款:usdt_invest_verify
         */
        private String tradeDetailType;

        /** 交易金额 */
        private BigDecimal tradeAmount;

        /** 交易审核时间 */
        private Date verifyTime;

        /**  */
        private String descr;

        /**  */
        private Date createTime;


}