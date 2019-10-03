package com.dream.city.base.model.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Wvv
 */
@Data
public class RelationTree {
    Integer id;
    String parentId;
    String playerId;
    String relation;
    /**
     * 是否自动发货
     * @return
     */
    String sendAuto;
    /**
     *   商会等级
     */
    Integer treeLevel;
    Timestamp createTime;
}
