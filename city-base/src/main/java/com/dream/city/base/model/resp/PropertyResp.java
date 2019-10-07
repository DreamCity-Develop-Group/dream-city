package com.dream.city.base.model.resp;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author
 */
@Data
public class PropertyResp implements Serializable {

    /** 标识 */
    private Integer inId;

    /** 名称 */
    private String inName;

    /** 项目图片地址 */
    private String inImg;

    /** 限额 */
    private BigDecimal inLimit;

    /** 开始时间 */
    private String inStart;

    /** 税金 */
    private Float inTax;

    /** 收益倍数 */
    private Integer inEarning;


    private String isValid;

    /** 投资结束时间 */
    private String inEnd;





}