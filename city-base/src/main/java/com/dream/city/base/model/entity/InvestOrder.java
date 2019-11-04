package com.dream.city.base.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Data
public class InvestOrder implements Serializable {
    /**  */
    private Integer orderId;

    /** 投资项目ID */
    private Integer orderInvestId;

    /** 玩家ID */
    private String orderPayerId;

    private String orderName;

    private String orderNum;

    /** 投资金额 */
    private BigDecimal orderAmount;

    /** 状态 */
    private Integer orderState;

    /** 是否复投 */
    private Integer orderRepeat;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InvestOrder that = (InvestOrder) o;
        return orderRepeat == that.orderRepeat &&
                orderId.equals(that.orderId) &&
                orderInvestId.equals(that.orderInvestId) &&
                orderPayerId.equals(that.orderPayerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderInvestId, orderPayerId, orderAmount, orderState, orderRepeat, createTime, updateTime);
    }

    /**  */
    private Date createTime;

    /**  */
    private Date updateTime;


}