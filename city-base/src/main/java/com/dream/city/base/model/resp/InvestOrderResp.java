package com.dream.city.base.model.resp;

import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 玩家投资订单
 */
@ToString
public class InvestOrderResp implements Serializable {

    /**
     * 订单id
     */
    private Integer orderId;

    /** 项目ID */
    private Integer investId;

    /** 名称 */
    private String inName;

    /** 玩家ID */
    private String payerId;
    private String playerName;
    private String playerNick;

    /** 订单状态 */
    private int orderState;

    /** 订单是否复投 */
    private int orderRepeat;

    /** 限额 */
    private Float inLimit;

    /** 税金 定额税*/
    private BigDecimal inTax;

    /** 个人所得税 */
    private BigDecimal personalInTax;
    /** 企业所得税 */
    private BigDecimal enterpriseIntax;
    /** 获利 */
     private BigDecimal profit;

    /** 收益倍数 */
    private String inEarning;

    /** 投资时间 */
    private Date createTime;


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getInvestId() {
        return investId;
    }

    public void setInvestId(Integer investId) {
        this.investId = investId;
    }

    public String getInName() {
        return inName;
    }

    public void setInName(String inName) {
        this.inName = inName;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public int getOrderRepeat() {
        return orderRepeat;
    }

    public void setOrderRepeat(int orderRepeat) {
        this.orderRepeat = orderRepeat;
    }

    public Float getInLimit() {
        return inLimit;
    }

    public void setInLimit(Float inLimit) {
        this.inLimit = inLimit;
    }

    public BigDecimal getInTax() {
        return inTax;
    }

    public void setInTax(BigDecimal inTax) {
        this.inTax = inTax;
    }

    public String getInEarning() {
        return inEarning;
    }

    public void setInEarning(String inEarning) {
        this.inEarning = inEarning;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerNick() {
        return playerNick;
    }

    public void setPlayerNick(String playerNick) {
        this.playerNick = playerNick;
    }

    public BigDecimal getPersonalInTax() {
        return personalInTax;
    }

    public void setPersonalInTax(BigDecimal personalInTax) {
        this.personalInTax = personalInTax;
    }

    public BigDecimal getEnterpriseIntax() {
        return enterpriseIntax;
    }

    public void setEnterpriseIntax(BigDecimal enterpriseIntax) {
        this.enterpriseIntax = enterpriseIntax;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }
}
