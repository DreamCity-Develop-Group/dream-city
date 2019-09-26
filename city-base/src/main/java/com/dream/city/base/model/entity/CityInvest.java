package com.dream.city.base.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CityInvest implements Serializable {
    /** 标识 */
    private Integer inId;

    /** 名称 */
    private String inName;

    /** 项目图片地址 */
    private String inImg;

    /** 限额 */
    private BigDecimal inLimit;

    /** 开始时间 */
    private Date inStart;

    /** 税金 */
    private Float inTax;

    /** 收益倍数 */
    private Integer inEarning;


    private String isValid;

    /** 投资结束时间 */
    private Date inEnd;

    private Date createTime;

    private Date updateTime;

    public Integer getInId() {
        return inId;
    }

    public void setInId(Integer inId) {
        this.inId = inId;
    }

    public String getInName() {
        return inName;
    }

    public void setInName(String inName) {
        this.inName = inName == null ? null : inName.trim();
    }

    public BigDecimal getInLimit() {
        return inLimit;
    }

    public void setInLimit(BigDecimal inLimit) {
        this.inLimit = inLimit;
    }

    public Date getInStart() {
        return inStart;
    }

    public void setInStart(Date inStart) {
        this.inStart = inStart;
    }

    public Float getInTax() {
        return inTax;
    }

    public void setInTax(Float inTax) {
        this.inTax = inTax;
    }

    public Integer getInEarning() {
        return inEarning;
    }

    public void setInEarning(Integer inEarning) {
        this.inEarning = inEarning == null ? 0 : inEarning;
    }

    public Date getInEnd() {
        return inEnd;
    }

    public void setInEnd(Date inEnd) {
        this.inEnd = inEnd;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getInImg() {
        return inImg;
    }

    public void setInImg(String inImg) {
        this.inImg = inImg;
    }
}