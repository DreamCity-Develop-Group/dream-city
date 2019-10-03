package com.dream.city.service;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.RelationTree;
import com.dream.city.base.model.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author Wvv
 */
public interface RelationTreeService {
    /**
     * 添加关系 A 推荐 B A为上级，B为下级
     * @param parent
     * @param child
     * @return
     */
    Result save(String parent, String child,String relation);

    Result updateTree(RelationTree tree);

    RelationTree get(String parent, String child);

    RelationTree getByPlayer(String playerId);

    RelationTree getPlayerByRef(String relation);

    List<RelationTree> findLevel(String playerId,Integer level);

    Map<Integer,List<RelationTree>> getLevelChildTreesMap(String playerId, int level);

    RelationTree getParent(String playerId);

    List<RelationTree> getTrees();

    Map<Integer,RelationTree> getParents(String playerId);

    boolean hasParent(String playerId);
}
