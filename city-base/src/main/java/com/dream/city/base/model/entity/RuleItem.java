package com.dream.city.base.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RuleItem implements Serializable {
    /**  */
    private Integer itemId;

    /** 规则项目名称 */
    private String itemName;

    /** 规则项目描述 */
    private String itemDesc;

    private String itemType;

    /** 可用状态 */
    private Byte itemState;

    /**  */
    private Date createTime;

    /**  */
    private Date updateTime;

}