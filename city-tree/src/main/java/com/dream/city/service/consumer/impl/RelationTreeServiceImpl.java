package com.dream.city.service.consumer.impl;

import com.alibaba.fastjson.JSONObject;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.model.CityGlobal;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.RelationTree;
import com.dream.city.base.model.entity.RuleItem;
import com.dream.city.base.model.enu.ReturnStatus;
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
    InvestRuleService investRuleService;

    @Autowired
    PlayerAccountService playerAccountService;

    @Autowired
    RedisUtils redisUtils;


    @LcnTransaction
    @Transactional
    @Override
    public Result save(String parent, String child, String invite) {
        try {
            RelationTree tree = new RelationTree();
            RelationTree parentTree = treeMapper.getTreeByPlayerId(parent);
            RelationTree playerTree = treeMapper.getTreeByPlayerId(child);
            if (StringUtils.isBlank(parent) || StringUtils.isBlank(child) || StringUtils.isBlank(invite)) {
                return Result.result(false, CityGlobal.Constant.TREE_PARAMS_ERROR, 501);
            }
            if (null == parentTree) {
                log.info("玩家上级邀请码关系不存在");
                return Result.result(false, "失败", 201);
            }
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
            }

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

                    if (result.getSuccess()) {
                        log.info("改变商会等级成功!");
                        //message.setTarget(playerTo.getPlayerName());
                        //主动推送消息，升级成功
                        JSONObject upgrade = new JSONObject();
                        upgrade.put("level", stars);
                        upgrade.put("toPlayerId", parent);
                        upgrade.put("username", playerTo.getPlayerName());
                        upgrade.put("fromPlayerId", child);
                        message.getData().setData(upgrade);
                        String json = JsonUtil.parseObjToJson(message);
                        log.info("升级：》" + json);
                        JSONObject ret = JSONObject.parseObject(json);
                        redisUtils.publishMsg(PlAYER_UPGRADE, json);
                    } else {
                        result = updateTree(parentTree);
                        if (result.getSuccess()) {
                            //message.setTarget(playerTo.getPlayerName());
                            log.info("改变商会等级成功!");
                            //主动推送消息，升级成功
                            JSONObject upgrade = new JSONObject();
                            upgrade.put("level", stars);
                            upgrade.put("toPlayerId", parent);
                            upgrade.put("username", playerTo.getPlayerName());
                            upgrade.put("fromPlayerId", child);
                            message.getData().setData(upgrade);
                            String json = JsonUtil.parseObjToJson(message);
                            JSONObject ret = JSONObject.parseObject(json);
                            redisUtils.publishMsg(PlAYER_UPGRADE, json);
                        } else {
                            result = updateTree(parentTree);
                            if (result.getSuccess()) {
                                //message.setTarget(playerTo.getPlayerName());
                                //主动推送消息，升级成功
                                JSONObject upgrade = new JSONObject();
                                upgrade.put("level", stars);
                                upgrade.put("toPlayerId", parent);
                                upgrade.put("username", playerTo.getPlayerName());
                                upgrade.put("fromPlayerId", child);
                                message.getData().setData(upgrade);
                                String json = JsonUtil.parseObjToJson(message);
                                JSONObject ret = JSONObject.parseObject(json);
                                redisUtils.publishMsg(PlAYER_UPGRADE, json);
                            }
                        }
                    }
                }

            }
            return Result.result(true, "成功", ReturnStatus.SUCCESS.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(false, CityGlobal.Constant.TREE_RELATION_EXISTS, ReturnStatus.EXISTS.getStatus());
        }

    }

    @LcnTransaction
    @Transactional
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
    @LcnTransaction
    @Transactional
    @Override
    public RelationTree getByPlayer(String playerId) {
        return treeMapper.getByPlayer(playerId);
    }

    @LcnTransaction
    @Transactional
    @Override
    public RelationTree getPlayerByRef(String relation) {
        return treeMapper.getTreeByRef(relation);
    }

    @LcnTransaction
    @Transactional
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
    @LcnTransaction
    @Transactional
    @Override
    public List<RelationTree> findLevel(String playerId, Integer level) {
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
    public Map<String, Object> getLevelChildTreesMap(String playerId, int level) {
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
        data.put("num", num);
        data.put("members", members);

        return data;
    }

    @LcnTransaction
    @Transactional
    @Override
    public RelationTree getParent(String playerId) {
        RelationTree player = treeMapper.getByPlayer(playerId);
        return treeMapper.getByPlayer(player.getTreeParentId());
    }

    @LcnTransaction
    @Transactional
    @Override
    public List<RelationTree> getTrees() {
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
    public RelationTree getTreeByPlayerId(String playerId) {
        return treeMapper.getTreeByPlayerId(playerId);
    }

    @LcnTransaction
    @Transactional
    @Override
    public void closeAutoSend(RelationTree tree) {
        treeMapper.updateTree(tree);
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
    public Map<Integer, RelationTree> getParents(String playerId) {
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
    public List<RelationTree> getChilds(String playerId) {
        List<RelationTree> trees = treeMapper.getChilds(playerId);

        return trees;
    }
}
