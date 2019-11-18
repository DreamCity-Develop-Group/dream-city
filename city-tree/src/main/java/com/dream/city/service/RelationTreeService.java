package com.dream.city.service;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.CityBusiness;
import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.base.model.entity.RelationTree;

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

    Map getAllParents(RelationTree childTree);

    void pushToParent(RelationTree parentTree, int stars, RelationTree child);

    RelationTree upgradeParent(RelationTree parent, int childsSize,List<InvestRule> rules);

    boolean upgradeParent(String parent, String child);

    Result updateTree(RelationTree tree);


    RelationTree get(String parent, String child);

    RelationTree getByPlayer(String playerId);

    RelationTree getPlayerByRef(String relation);

    List<RelationTree> findLevel(String playerId,Integer level);

    Map<String,Object> getLevelChildTreesMap(String playerId, int level);

    RelationTree getParent(String playerId);

    List<RelationTree> getTrees();

    Map<Integer,RelationTree> getParents(String playerId);

    List<RelationTree> getChilds(String playerId);

    boolean hasParent(String playerId);

    RelationTree getTreeByPlayerId(String playerId);

    void closeAutoSend(RelationTree tree);

    void addCityBusiness(CityBusiness cityBusiness);

    CityBusiness getEnabledCityBusiness(String playerId);
}
