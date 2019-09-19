package com.dream.city.base.model.entity;

import java.io.Serializable;
import java.util.Date;

public class RuleItem implements Serializable {
    /**  */
    private Integer itemId;

    /** 规则项目名称 */
    private String itemName;

    /** 规则项目描述 */
    private String itemDesc;

    /** 可用状态 */
    private Byte itemState;

    /**  */
    private Date createTime;

    /**  */
    private Date updateTime;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc == null ? null : itemDesc.trim();
    }

    public Byte getItemState() {
        return itemState;
    }

    public void setItemState(Byte itemState) {
        this.itemState = itemState;
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
}