package com.dream.city.service;

import com.dream.city.base.model.entity.RelationTree;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface RelationTreeService {
    int getMembersIncrement(String orderPayerId, Date endTime);

    Map<Integer, List<RelationTree>> getLevelChildTreesMap(String playerId, int level);


    RelationTree getParent(String playerId);
    boolean hasParent(String playerId);

    RelationTree getSelfTree(String playerId);

    Integer getTeamListCount(String tree, String startTime, String endTime);
}
