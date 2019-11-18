package com.dream.city.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.CommerceRelation;
import com.dream.city.base.model.entity.RelationTree;
import com.dream.city.base.model.mapper.CommerceRelationMapper;
import com.dream.city.base.model.mapper.RelationTreeMapper;
import com.dream.city.service.RelationTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Wvv
 */

@Service
public class RelationTreeServiceImpl implements RelationTreeService {
    @Autowired
    private CommerceRelationMapper commerceRelationMapper;

    @Autowired
    private RelationTreeMapper treeMapper;

    @LcnTransaction
    @Transactional
    @Override
    public int getMembersIncrement(String orderPayerId, Date date) throws BusinessException {
        Map<Integer, List<RelationTree>> trees = getLevelChildTreesMap(orderPayerId, 9);

        List<RelationTree> relationTrees = new ArrayList<>();
        trees.forEach((key, tree) -> {
            if (tree.size()>0){
                tree.forEach((t) -> {
                    try {
                        /**
                         *TODO
                         *  在今天截止预约之前 && 并且 && 在昨天预约开始之后
                         */
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        //时分格式
                        SimpleDateFormat df1 = new SimpleDateFormat("HH:mm:ss");
                        //年月日格式
                        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
                        String date1 = df1.format(date);
                        String dateToday = dt.format(new Date());
                        String end = dateToday + " " + date1;
                        Date endTime = df.parse(end);

                        System.out.println(date1);
                        System.out.println(dateToday);

                        Calendar cal = Calendar.getInstance();
                        cal.add(Calendar.DATE, -1);
                        String yesterday = dt.format(cal.getTime());
                        String start = yesterday + " " + date1;


                        System.out.println(end);
                        System.out.println(start);

                        Date startTime = df.parse(start);
                        if (t.getCreateTime().after(startTime) && t.getCreateTime().before(endTime)) {
                            relationTrees.add(t);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                });
            }
        });

        return relationTrees.size();
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
    public Map<Integer, List<RelationTree>> getLevelChildTreesMap(String playerId, int level) throws BusinessException {
        Map<Integer, List<RelationTree>> treeListMap = new Hashtable<>();
        String rel = "";
        for (int i = 0; i < level; i++) {
            RelationTree tree = treeMapper.getByPlayer(playerId);
            String relation = tree.getTreeRelation();
            rel=rel+"/______";
            List<RelationTree> levelTrees = treeMapper.selectByRelation(relation+rel);
            treeListMap.put(i + 1, levelTrees);
        }

        return treeListMap;
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
    public boolean hasParent(String playerId) {
        RelationTree tree = getParent(playerId);
        return tree != null;
    }

    @Override
    public RelationTree getSelfTree(String playerId) {
        return treeMapper.getSelfTree(playerId);
    }

    @Override
    public Integer getTeamListCount(String tree, String startTime, String endTime) {
        return treeMapper.getTeamListCount(tree,startTime,endTime);
    }


}
