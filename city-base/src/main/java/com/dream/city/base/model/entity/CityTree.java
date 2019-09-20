package com.dream.city.base.model.entity;

import java.io.Serializable;

public class CityTree implements Serializable {
    /**  */
    private Integer treeId;

    /** 上级ID */
    private Integer treeParentId;

    /** 当前用户ID */
    private Integer treePlayerId;

    /** 关系网络 */
    private String treeRelation;

    public Integer getTreeId() {
        return treeId;
    }

    public void setTreeId(Integer treeId) {
        this.treeId = treeId;
    }

    public Integer getTreeParentId() {
        return treeParentId;
    }

    public void setTreeParentId(Integer treeParentId) {
        this.treeParentId = treeParentId;
    }

    public Integer getTreePlayerId() {
        return treePlayerId;
    }

    public void setTreePlayerId(Integer treePlayerId) {
        this.treePlayerId = treePlayerId;
    }

    public String getTreeRelation() {
        return treeRelation;
    }

    public void setTreeRelation(String treeRelation) {
        this.treeRelation = treeRelation == null ? null : treeRelation.trim();
    }
}