package com.dream.city.base.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CityTree implements Serializable {
    /**
     *
     */
    private Integer treeId;

    /**
     * 上级ID
     */
    private Integer treeParentId;

    /**
     * 当前用户ID
     */
    private Integer treePlayerId;

    /**
     * 关系网络
     */
    private String treeRelation;

    /**
     * 是否自动发货
     *
     * @return
     */
    private String sendAuto;

}