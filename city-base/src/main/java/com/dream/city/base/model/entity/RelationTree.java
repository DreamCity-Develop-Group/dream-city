package com.dream.city.base.model.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Wvv
 */
@Data
public class RelationTree {
    /**
     *
     */
    private Integer treeId;

    /**
     * 上级ID
     */
    private String treeParentId;

    /**
     * 当前用户ID
     */
    private String treePlayerId;

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
    /**
     *   商会等级
     */
    Integer treeLevel;
    Timestamp createTime;
}
