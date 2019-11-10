package com.dream.city.base.model.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Wvv
 */
@Data
public class CityBusiness {
    /**
     *
     */
    private Integer businessId;

    /**
     * 上级ID
     */
    private String businessParentId;

    /**
     * 当前用户ID
     */
    private String businessPlayerId;

    /**
     * 关系网络
     */
    private String businessRelation;

    /**
     * 是否可用 1 可用 0不可用
     */
    private Integer businessEnabled;

}
