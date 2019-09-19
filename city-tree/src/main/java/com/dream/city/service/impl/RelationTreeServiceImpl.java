package com.dream.city.service.impl;

import com.dream.city.base.model.Result;
import com.dream.city.domain.entity.RelationTree;
import com.dream.city.domain.entity.User;
import com.dream.city.domain.mapper.TreeMapper;
import com.dream.city.domain.mapper.UserMapper;
import com.dream.city.service.RelationTreeService;
import com.dream.city.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * @author Wvv
 */
@Service
public class RelationTreeServiceImpl implements RelationTreeService {
    @Autowired
    TreeMapper treeMapper;

    @Override
    public Result save(String parent, String child, String invite) {
        RelationTree tree = new RelationTree();
        RelationTree parentTree = treeMapper.getByPlayer(parent);
        tree.setId(0);
        tree.setParentId(parent);
        tree.setPlayerId(child);
        tree.setRelation(parentTree.getRelation() + "/" + invite);
        treeMapper.saveTree(tree);

        return new Result("成功", 200);
    }


    @Override
    public RelationTree get(String parent, String child) {
        return treeMapper.get(parent, child);
    }

    /**
     * 获取当前玩家
     * @param playerId
     * @return
     */
    @Override
    public RelationTree getByPlayer(String playerId) {
        return treeMapper.getByPlayer(playerId);
    }

    /**
     * 找出对应级别所有子对象
     *
     * @param playerId SELECT * FROM city_tree WHERE tree_relation like 'wew32d/______/______/______'
     * @param level
     * @return
     */
    @Override
    public List<RelationTree> findLevel(String playerId, Integer level) {
        if (level <= 1){
            return treeMapper.getByParent(playerId);
        }
        String like = "";
        for (int i = 0; i < level; i++) {
            like += "/______";
        }
        RelationTree tree = treeMapper.getByPlayer(playerId);

        like = tree.getRelation() + like;
        System.out.println("Select Like: "+like);
        List<RelationTree> trees = treeMapper.selectByRelation(like);

        return  trees;
    }

    /**
     * 找出指定层级数量的所有子对象
     *
     * @param playerId
     * @param level
     * @return
     */
    @Override
    public Map<Integer,List<RelationTree>> getLevelChildTreesMap(String playerId, int level) {
        Map<Integer,List<RelationTree>> treeListMap = new Hashtable<>();
        for (int i=0;i<level;i++){
            List<RelationTree> levelTree= this.findLevel(playerId,i+1);
            treeListMap.put(i+1,levelTree);
        }

        return treeListMap;
    }

    @Override
    public RelationTree getParent(String playerId){
        RelationTree player =  treeMapper.getByPlayer(playerId);
        return treeMapper.getByPlayer(player.getParentId());
    }
}
