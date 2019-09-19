package com.dream.city.domain.mapper;

import com.dream.city.base.model.entity.RelationTree;
import com.dream.city.base.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wvv
 */
@Repository
public interface TreeMapper {
    RelationTree getTreeById(Integer id);
    RelationTree get(String pid,String cid);
    void  saveTree(RelationTree tree);
    void saveTreeMap(Integer parent,Integer child,String relation);
    List<RelationTree> getTrees();
    void updateTree();
    void deleteTree(Integer id);
    void disableTree();

    List<RelationTree> getByParent(String parentId);

    RelationTree getByPlayer(String playerId);

    List<RelationTree> selectByRelation(String relation);





}
