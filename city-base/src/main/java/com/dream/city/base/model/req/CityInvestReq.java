package com.dream.city.base.model.req;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CityInvestReq implements Serializable {

    private Integer inId;
    private Integer orderId;
    /** 名称 */
    private String inName;
    private String isValid;
    private Integer inType;

    /** 玩家ID */
    private String playerId;
    private String playerName;
    private String playerNick;

    private String friendId;

    /** 开始时间 */
    private String inStart;

    /** 投资结束时间 */
    private String inEnd;

    /** 限额 */
    private BigDecimal inLimit;
    /** 税金 */
    private BigDecimal inPersonalTax;
    /** 税金 */
    private BigDecimal inEnterpriseTax;
    /** 定额税 */
    private BigDecimal inQuotaTax;

    /** 收益倍数 */
    private BigDecimal inEarning;


}