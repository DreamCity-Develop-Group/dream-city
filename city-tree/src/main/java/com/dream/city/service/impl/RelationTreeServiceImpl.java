package com.dream.city.service.impl;

import com.dream.city.base.model.CityGlobal;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.base.model.entity.RelationTree;
import com.dream.city.base.model.entity.RuleItem;
import com.dream.city.base.model.mapper.CityTreeMapper;
import com.dream.city.service.InvestRuleService;
import com.dream.city.service.PlayerAccountService;
import com.dream.city.service.RelationTreeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * @author Wvv
 */
@Service
@Slf4j
public class RelationTreeServiceImpl implements RelationTreeService {

    private final String PlAYER_FLAG = "PlAYER_LEVEL";

    @Autowired
    private CityTreeMapper treeMapper;

    @Autowired
    InvestRuleService investRuleService;

    @Autowired
    PlayerAccountService playerAccountService;

    @Override
    public Result save(String parent, String child, String invite) {
        RelationTree tree = new RelationTree();
        RelationTree parentTree = treeMapper.getByPlayer(parent);
        RelationTree playerTree = treeMapper.getByPlayer(child);
        if (StringUtils.isBlank(parent) || StringUtils.isBlank(child) || StringUtils.isBlank(invite)) {
            return Result.result(false, CityGlobal.Constant.TREE_PARAMS_ERROR, 501);
        }
        if (null == parentTree) {
            log.info("玩家上级邀请码关系不存在");
            return Result.result(false, "失败", 201);
        }
        if (null == playerTree) {
            tree.setId(0);
            tree.setParentId(parent);
            tree.setPlayerId(child);
            tree.setRelation(parentTree.getRelation() + "/" + invite);
            tree.setTreeLevel(0);
            treeMapper.saveTree(tree);
            log.info("保存树成功");

            /**
             *TODO
             * 保存商会成员时根据成员数量变更商会等级
             *
             */
            List<RelationTree> childs = treeMapper.getChilds(parent);
            int childsSize = childs.size();
            int stars = 0;
            RuleItem ruleItem = investRuleService.getRuleItemByFlag(PlAYER_FLAG);
            List<InvestRule> rules = investRuleService.getRulesByItem(ruleItem.getItemId());
            for (InvestRule rule : rules){
                if (rule.getRuleOpt() == "OPT_NUM" && rule.getRuleRate() == childsSize){
                    stars = rule.getRuleLevel();
                    break;
                }
            }
            if (stars>0){
                parentTree.setTreeLevel(stars);
                Result result = updateTree(parentTree);
                playerAccountService.updatePlayerLevel(parent,stars);

                if (result.getSuccess()){
                    log.info("改变商会等级成功!");
                }else {
                    updateTree(parentTree);
                }
            }


            return Result.result(true, "成功", 200);
        }
        return Result.result(false, CityGlobal.Constant.TREE_RELATION_EXISTS, 201);

    }


    @Override
    public RelationTree get(String parent, String child) {
        return treeMapper.get(parent, child);
    }

    /**
     * 获取当前玩家
     *
     * @param playerId
     * @return
     */
    @Override
    public RelationTree getByPlayer(String playerId) {
        return treeMapper.getByPlayer(playerId);
    }

    @Override
    public RelationTree getPlayerByRef(String relation) {
        return treeMapper.getTreeByRef(relation);
    }

    @Override
    public Result updateTree(RelationTree tree) {
        treeMapper.updateTree(tree);
        return Result.result(true);
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
        String like = "";
        for (int i = 0; i < level; i++) {
            like += "/______";
        }
        RelationTree tree = treeMapper.getByPlayer(playerId);

        like = tree.getRelation() + like + "";
        System.out.println("Select Like: " + like);
        List<RelationTree> trees = treeMapper.selectByRelation(like);

        return trees;
    }

    /**
     * 找出指定层级数量的所有子对象
     *
     * @param playerId
     * @param level
     * @return
     */
    @Override
    public Map<Integer, List<RelationTree>> getLevelChildTreesMap(String playerId, int level) {
        Map<Integer, List<RelationTree>> treeListMap = new Hashtable<>();
        for (int i = 0; i < level; i++) {
            List<RelationTree> levelTree = this.findLevel(playerId, i + 1);
            treeListMap.put(i + 1, levelTree);
        }

        return treeListMap;
    }

    @Override
    public RelationTree getParent(String playerId) {
        RelationTree player = treeMapper.getByPlayer(playerId);
        return treeMapper.getByPlayer(player.getParentId());
    }

    @Override
    public List<RelationTree> getTrees() {
        return treeMapper.getTrees();
    }

    @Override
    public boolean hasParent(String playerId) {
        RelationTree tree = getParent(playerId);
        return tree != null;
    }

    /**
     * 获取以上9级的父结点
     *
     * @param playerId
     * @return
     */
    @Override
    public Map<Integer, RelationTree> getParents(String playerId) {
        Map<Integer, RelationTree> trees = new Hashtable<>();

        RelationTree self = getByPlayer(playerId);
        String selfRef = self.getRelation();
        String[] relations = selfRef.split("/");
        Integer levels = relations.length;
        Integer level = 0;
        while (levels > 0 && (level + 1) < 9) {
            level = level + 1;

            int start = 0;
            int end = 7 * (levels - level) - 1;

            String parentRef = selfRef.substring(start, end);
            RelationTree parent = getPlayerByRef(parentRef);
            trees.put(level, parent);
        }

        return trees;
    }
}
