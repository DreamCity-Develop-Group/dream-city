package com.dream.city.service;

import com.dream.city.base.model.entity.CommerceRelation;
import com.dream.city.base.model.entity.RelationTree;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface RelationTreeService {
    int getMembersIncrement(String orderPayerId, Date endTime);

    Map<Integer, List<RelationTree>> getLevelChildTreesMap(String playerId, int level);

    List<RelationTree> findLevel(String playerId, int i);

    RelationTree getParent(String playerId);
    boolean hasParent(String playerId);
}
