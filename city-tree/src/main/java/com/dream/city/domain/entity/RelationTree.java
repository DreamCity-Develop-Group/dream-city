package com.dream.city.domain.entity;

import lombok.Data;

/**
 * @author Wvv
 */
@Data
public class RelationTree {
    Integer id;
    String parentId;
    String playerId;
    String relation;
}
