package com.dream.city.base.model.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Wvv
 */
@Data
public class CommerceRelation {
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

    public Integer getTreeId() {
        return treeId;
    }

    public void setTreeId(Integer treeId) {
        this.treeId = treeId;
    }

    public String getTreeParentId() {
        return treeParentId;
    }

    public void setTreeParentId(String treeParentId) {
        this.treeParentId = treeParentId;
    }

    public String getTreePlayerId() {
        return treePlayerId;
    }

    public void setTreePlayerId(String treePlayerId) {
        this.treePlayerId = treePlayerId;
    }

    public String getTreeRelation() {
        return treeRelation;
    }

    public void setTreeRelation(String treeRelation) {
        this.treeRelation = treeRelation;
    }

    public String getSendAuto() {
        return sendAuto;
    }

    public void setSendAuto(String sendAuto) {
        this.sendAuto = sendAuto;
    }

    public Integer getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(Integer treeLevel) {
        this.treeLevel = treeLevel;
    }

    public Integer getTreeChilds() {
        return treeChilds;
    }

    public void setTreeChilds(Integer treeChilds) {
        this.treeChilds = treeChilds;
    }

    public Integer getTreeGrandson() {
        return treeGrandson;
    }

    public void setTreeGrandson(Integer treeGrandson) {
        this.treeGrandson = treeGrandson;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *   商会等级
     */
    Integer treeLevel;

    /**
     *   成员数
     */
    Integer treeChilds;
    /**
     *   间接成员数
     */
    Integer treeGrandson;

    Date createTime;
}
