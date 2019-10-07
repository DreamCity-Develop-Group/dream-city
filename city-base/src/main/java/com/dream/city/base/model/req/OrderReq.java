package com.dream.city.base.model.req;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderReq implements Serializable {

    /** 订单id */
    private Integer id;

    /** 投资项目名称 */
    private String inName;

    /** 玩家Name */
    private String payerName;

    /** 投资金额 */
    private BigDecimal orderAmount;

    /** 状态 */
    private String orderState;

    /** 是否复投 */
    private Integer orderRepeat;



}