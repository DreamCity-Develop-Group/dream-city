package com.dream.city.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.CityGlobal;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.*;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.model.mapper.CityBusinessMapper;
import com.dream.city.base.model.mapper.RelationTreeMapper;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.base.utils.RedisKeys;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.InvestRuleService;
import com.dream.city.service.PlayerAccountService;
import com.dream.city.service.RelationTreeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Wvv
 */
@Service
@Slf4j
public class RelationTreeServiceImpl implements RelationTreeService {

    private final String PlAYER_FLAG = "PlAYER_LEVEL";
    private final String PlAYER_UPGRADE = "PUSHER_CHANNEL";

    @Autowired
    private RelationTreeMapper treeMapper;

    @Autowired
    private CityBusinessMapper cityBusinessMapper;


    @Autowired
    InvestRuleService investRuleService;

    @Autowired
    PlayerAccountService playerAccountService;

    @Autowired
    RedisUtils redisUtils;


    @LcnTransaction(propagation = DTXPropagation.REQUIRED)
    @Transactional
    @Override
    public Result save(String parent, String child, String invite) throws BusinessException {
        try {
            RelationTree tree = new RelationTree();
            RelationTree parentTree = treeMapper.getTreeByPlayerId(parent);
            RelationTree playerTree = treeMapper.getTreeByPlayerId(child);
            //参数判断
            if (StringUtils.isBlank(parent) || StringUtils.isBlank(child) || StringUtils.isBlank(invite)) {
                return Result.result(false, CityGlobal.Constant.TREE_PARAMS_ERROR, 501);
            }
            //上级玩家空判断
            if (null == parentTree) {
                log.info("玩家上级邀请码关系不存在");
                return Result.result(false, "失败", 201);
            }
            //存在判断
            if (null == playerTree) {
                tree.setTreeId(0);
                tree.setTreeParentId(parent);
                tree.setTreePlayerId(child);
                tree.setTreeRelation(parentTree.getTreeRelation() + "/" + invite);
                tree.setSendAuto("");
                tree.setTreeLevel(0);
                tree.setCreateTime(new Timestamp(System.currentTimeMillis()));
                treeMapper.saveTree(tree);
                log.info("保存树成功");
                //playerTree = tree;
            }

            /**
             *TODO
             * 保存商会成员时根据成员数量变更商会等级
             *
             * 1、首先是上级玩家
             * 2、然后是上上级玩家至上N级玩家
             *
             */
            RuleItem ruleItem = investRuleService.getRuleItemByFlag(PlAYER_FLAG);
            List<InvestRule> rules = investRuleService.getRulesByItem(ruleItem.getItemId());
            Map<Integer, RelationTree> parents;
            //找出所有上级，不包括自己
            if(playerTree==null){
                playerTree = tree;
                parents = getAllParents(playerTree);

            }else{
                parents = getAllParents(playerTree);
            }

            for(Map.Entry<Integer,RelationTree> entry : parents.entrySet()){
                try {
                    RelationTree value = entry.getValue();
                    //直接下级
                    int childNum = value.getTreeChilds()==null?0:value.getTreeChilds();
                    int gNum =  value.getTreeGrandson()==null?0:value.getTreeGrandson();
                    if (playerTree.getTreeParentId().equalsIgnoreCase(value.getTreePlayerId())) {

                        value.setTreeChilds( childNum + 1);
                        value.setTreeGrandson( gNum);
                    } else {
                        value.setTreeChilds( childNum);
                        value.setTreeGrandson( gNum+ 1);
                    }
                    //下级人员总数
                    int childsSize = value.getTreeChilds() + value.getTreeGrandson();

                    RelationTree currentParentTree = upgradeParent(value, childsSize,rules);

                    Result result = updateTree(currentParentTree);
                    if (!result.getSuccess()){
                        throw new BusinessException("升级玩家["+value.getTreePlayerId()+"]出错");
                    }
                } catch (Exception e) {
                    throw new BusinessException("升级出错");
                }
            }
            /*parents.forEach((key, value) -> {
                try {
                    //直接下级
                    if (playerTree.getTreeParentId().equalsIgnoreCase(value.getTreePlayerId())) {
                        value.setTreeChilds(value.getTreeChilds() + 1);
                    } else {
                        value.setTreeGrandson(value.getTreeGrandson() + 1);
                    }
                    //下级人员总数
                    int childsSize = value.getTreeChilds() + value.getTreeGrandson();

                    RelationTree currentParentTree = upgradeParent(value, childsSize,rules);

                    Result result = updateTree(currentParentTree);
                    if (!result.getSuccess()){
                        throw new BusinessException("升级玩家["+value.getTreePlayerId()+"]出错");
                    }
                } catch (Exception e) {
                    throw new BusinessException("升级出错");
                }
            });*/

            return Result.result(true, "成功", ReturnStatus.SUCCESS.getStatus());

        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(false, CityGlobal.Constant.TREE_RELATION_EXISTS, ReturnStatus.EXISTS.getStatus());
        }

    }

    public Map getAllParents(RelationTree childTree) {
        Map<Integer, RelationTree> parents = new HashMap<>();
        String relation = childTree.getTreeRelation();
        int length = (relation.length() + 1) / 7; //==>8

        for (int i = 0; i < length - 1; i++) {
            String parentRelation = relation.substring(0, 7 * (length - 1 - i) - 1);

            RelationTree parent = getPlayerByRef(parentRelation);
            parents.put(i + 1, parent);
        }

        return parents;
    }

    public void pushToParent(RelationTree parentTree, int stars, RelationTree child) {
        Message message = new Message(
                "server",
                "client",
                new MessageData("push", "comm", new JSONObject(), ReturnStatus.SUCCESS.getStatus()),
                "升级成功"
        );
        Player playerTo = playerAccountService.getPlayerByPlayerId(parentTree.getTreePlayerId());
        boolean isPresent = redisUtils.get(RedisKeys.PLAYER_ONLINE_STATE_KEY + playerTo.getPlayerName()).isPresent();
        String clientId = "";
        if (isPresent) {
            clientId = redisUtils.get(RedisKeys.PLAYER_ONLINE_STATE_KEY + playerTo.getPlayerName()).get();
            message.setTarget(clientId);
            message.getData().setCode(ReturnStatus.UPGRADE_TIP.getStatus());
            message.setDesc(ReturnStatus.UPGRADE_TIP.getDesc());


            log.info("改变商会等级成功!");
            //message.setTarget(playerTo.getPlayerName());
            //主动推送消息，升级成功
            JSONObject upgrade = new JSONObject();
            upgrade.put("level", stars);
            upgrade.put("toPlayerId", parentTree.getTreePlayerId());
            upgrade.put("username", playerTo.getPlayerName());
            upgrade.put("fromPlayerId", child.getTreePlayerId());
            message.getData().setData(upgrade);
            String json = JsonUtil.parseObjToJson(message);
            log.info("升级：》" + json);
            JSONObject ret = JSONObject.parseObject(json);
            redisUtils.publishMsg(PlAYER_UPGRADE, json);

        }
    }

    /**
     * 更新上级级别
     *
     * @param parent
     * @param childsSize
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public RelationTree upgradeParent(RelationTree parent, int childsSize,List<InvestRule> rules) throws BusinessException {
        try {
            int stars = 0;

            for (InvestRule rule : rules) {
                if ("OPT_NUM".equals(rule.getRuleOpt())) {
                    if (rule.getRuleRate().compareTo(new BigDecimal(childsSize)) != 1) {
                        stars = rule.getRuleLevel();
                    }
                }
            }
            if (stars > 0 && stars > parent.getTreeLevel()) {
                parent.setTreeLevel(stars);
            }
            return parent;
        } catch (Exception e) {
            return parent;
        }
    }

    /**
     * 更新上级级别
     *
     * @param parent
     * @param child
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public boolean upgradeParent(String parent, String child) throws BusinessException {
        try {
            RelationTree parentTree = getTreeByPlayerId(parent);
            RelationTree childTree = getTreeByPlayerId(child);

            List<RelationTree> childs = treeMapper.getChilds(parent);
            int childsSize = childs.size();
            int stars = 0;
            RuleItem ruleItem = investRuleService.getRuleItemByFlag(PlAYER_FLAG);
            List<InvestRule> rules = investRuleService.getRulesByItem(ruleItem.getItemId());
            for (InvestRule rule : rules) {
                if ("OPT_NUM".equals(rule.getRuleOpt())) {
                    if (rule.getRuleRate().compareTo(new BigDecimal(childsSize)) != 1) {
                        stars = rule.getRuleLevel();
                    }
                }
            }
            if (stars > 0 && stars > parentTree.getTreeLevel()) {
                parentTree.setTreeLevel(stars);
                Result result = updateTree(parentTree);
                playerAccountService.updatePlayerLevel(parent, stars);

                if (result.getSuccess()) {
                    pushToParent(parentTree, stars, childTree);
                } else {
                    result = updateTree(parentTree);
                    if (result.getSuccess()) {
                        pushToParent(parentTree, stars, childTree);
                    } else {
                        result = updateTree(parentTree);

                    }
                }

            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @LcnTransaction
    @Transactional
    @Override
    public RelationTree get(String parent, String child) throws BusinessException {
        return treeMapper.get(parent, child);
    }

    /**
     * 获取当前玩家
     *
     * @param playerId
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public RelationTree getByPlayer(String playerId) throws BusinessException {
        return treeMapper.getByPlayer(playerId);
    }

    @LcnTransaction
    @Transactional
    @Override
    public RelationTree getPlayerByRef(String relation) throws BusinessException {
        return treeMapper.getTreeByRef(relation);
    }

    @LcnTransaction
    @Transactional
    @Override
    public Result updateTree(RelationTree tree) throws BusinessException {
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
    @LcnTransaction
    @Transactional
    @Override
    public List<RelationTree> findLevel(String playerId, Integer level) throws BusinessException {
        String like = "";
        for (int i = 0; i < level; i++) {
            like += "/______";
        }
        RelationTree tree = treeMapper.getTreeByPlayerId(playerId);

        like = tree.getTreeRelation() + like + "";
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
    @LcnTransaction
    @Transactional
    @Override
    public Map<String, Object> getLevelChildTreesMap(String playerId, int level) throws BusinessException {
        Map<Integer, List<RelationTree>> treeListMap = new Hashtable<>();
        for (int i = 0; i < level; i++) {
            List<RelationTree> levelTree = this.findLevel(playerId, i + 1);
            treeListMap.put(i + 1, levelTree);
        }

        int num = 0;
        Map<String, Object> data = new HashMap<>();
        List<Map<String, String>> members = new ArrayList<>();
        Iterator<Map.Entry<Integer, List<RelationTree>>> entries = treeListMap.entrySet().iterator();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        while (entries.hasNext()) {
            Map.Entry<Integer, List<RelationTree>> entry = entries.next();
            Integer key = entry.getKey();
            List<RelationTree> value = entry.getValue();
            if (key > 1) {
                num += value.size();
            } else {
                for (RelationTree tree : value) {
                    Player player = playerAccountService.getPlayerByPlayerId(tree.getTreePlayerId());
                    Map<String, String> p = new HashMap<>();
                    p.put("playerId", player.getPlayerId());
                    //String pre = player.getPlayerName().substring(0,2);
                    //String end = player.getPlayerName().substring(7,-1);
                    p.put("playerName", player.getPlayerNick());
                    Date date = player.getCreateTime() == null ? new Date() : player.getCreateTime();
                    String date1 = simpleDateFormat.format(date);
                    p.put("createTime", date1);
                    members.add(p);
                }

            }
        }
        RelationTree tree = getByPlayer(playerId);
        data.put("core",tree.getTreeChilds()==null?0:tree.getTreeChilds());
        data.put("num", tree.getTreeGrandson()==null?0:tree.getTreeGrandson());
        data.put("members", members);

        return data;
    }

    @LcnTransaction
    @Transactional
    @Override
    public RelationTree getParent(String playerId) throws BusinessException {
        RelationTree player = treeMapper.getByPlayer(playerId);
        return treeMapper.getByPlayer(player.getTreeParentId());
    }

    @LcnTransaction
    @Transactional
    @Override
    public List<RelationTree> getTrees() throws BusinessException {
        return treeMapper.getTrees();
    }

    @LcnTransaction
    @Transactional
    @Override
    public boolean hasParent(String playerId) {
        RelationTree tree = getParent(playerId);
        return tree != null;
    }

    @LcnTransaction
    @Transactional
    @Override
    public RelationTree getTreeByPlayerId(String playerId) throws BusinessException {
        return treeMapper.getTreeByPlayerId(playerId);
    }

    @LcnTransaction
    @Transactional
    @Override
    public void closeAutoSend(RelationTree tree) throws BusinessException {
        treeMapper.updateTree(tree);
    }

    @Override
    public void addCityBusiness(CityBusiness cityBusiness) {
        cityBusinessMapper.savebusiness(cityBusiness);
    }

    @Override
    public CityBusiness getEnabledCityBusiness(String playerId) {
        return cityBusinessMapper.getEnabledCityBusiness(playerId);
    }

    /**
     * 获取以上9级的父结点
     *
     * @param playerId
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Map<Integer, RelationTree> getParents(String playerId) throws BusinessException {
        Map<Integer, RelationTree> trees = new Hashtable<>();

        RelationTree self = this.getTreeByPlayerId(playerId);
        String selfRef = self.getTreeRelation();
        String[] relations = selfRef.split("/");
        int levels = relations.length;
        int level = 0;
        while (levels > 0 && (level + 1) < 10) {
            level = level + 1;

            int start = 0;
            int end = 7 * (levels - level) - 1;
            if (end < 0) {
                break;
            }
            String parentRef = selfRef.substring(start, end);
            RelationTree parent = getPlayerByRef(parentRef);
            trees.put(level, parent);
        }

        return trees;
    }

    @LcnTransaction
    @Transactional
    @Override
    public List<RelationTree> getChilds(String playerId) throws BusinessException {
        List<RelationTree> trees = treeMapper.getChilds(playerId);

        return trees;
    }
}
