package com.dream.city.player.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Page;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.InvestOrder;
import com.dream.city.base.model.entity.PlayerLikes;
import com.dream.city.base.model.entity.PlayerLikesLog;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.model.mapper.InvestOrderMapper;
import com.dream.city.base.model.req.PlayerLikesReq;
import com.dream.city.base.model.mapper.PlayerLikesLogMapper;
import com.dream.city.base.model.mapper.PlayerLikesMapper;
import com.dream.city.base.model.resp.PlayerLikesResp;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.player.service.LikesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class LikesServiceImpl implements LikesService {


    @Autowired
    PlayerLikesMapper playerLikesMapper;
    @Autowired
    PlayerLikesLogMapper likesLogMapper;
    @Autowired
    InvestOrderMapper investOrderMapper;


    @LcnTransaction
    @Transactional
    @Override
    public int playerLike(PlayerLikesReq record) throws BusinessException {
        Integer i = 0;
        if (record.getLikedId() == null) {
            record.setCreateTime(new Date());
            i = playerLikesMapper.insertSelective(record);
        } else {
            int count = getLikeCount(record.getLikedId());
            record.setUpdateTime(new Date());
            record.setLikedSetTotal(count + record.getLikedSetTotal());
            i = playerLikesMapper.updateByPrimaryKeySelective(record);
        }
        if (i > 0) {
            savePlayerLikesLog(record);
        }
        return i == null ? 0 : i;
    }


    @LcnTransaction
    @Transactional
    @Override
    public int cancelLike(PlayerLikesReq record) throws BusinessException {
        int count = getLikeCount(record.getLikedId());
        record.setUpdateTime(new Date());
        record.setLikedSetTotal(count > 0 ? (count - 1) : count);
        Integer i = playerLikesMapper.updateByPrimaryKeySelective(record);
        if (i > 0) {
            savePlayerLikesLog(record);
        }
        return i == null ? 0 : i;
    }

    @LcnTransaction
    @Transactional
    @Override
    public int playerLikesCount(PlayerLikesReq record) throws BusinessException {
        Integer likesCount = playerLikesMapper.playerLikesCount(record);
        return likesCount == null ? 0 : likesCount;
    }

    @LcnTransaction
    @Transactional
    @Override
    public PageInfo<PlayerLikesResp> playerLikesList(Page record) throws BusinessException {
        PlayerLikesReq likesReq = DataUtils.toJavaObject(record.getCondition(), PlayerLikesReq.class);
        PageHelper.startPage(record.getPageNum(), record.getPageSize(), record.isCount());
        List<PlayerLikesResp> likesList = playerLikesMapper.playerLikesList(likesReq);
        return new PageInfo<>(likesList);
    }

    @LcnTransaction
    @Transactional
    @Override
    public int playerLikesCountToday(PlayerLikesReq record) throws BusinessException {
        PlayerLikesLog likesLog = new PlayerLikesLog();
        likesLog.setLikeLikedId(record.getLikedPlayerId());
        likesLog.setLikePlayerId(record.getLikePlayerId());
        return likesLogMapper.playerLikesCountToday(likesLog);
    }

    @LcnTransaction
    @Transactional
    @Override
    public int investLikesCountToday(PlayerLikesReq record) throws BusinessException {
        PlayerLikesLog likesLog = new PlayerLikesLog();
        likesLog.setLikeLikedId(record.getLikedPlayerId());
        likesLog.setLikeInvestId(record.getLikedInvestId());
        likesLog.setLikePlayerId(record.getLikePlayerId());
        return likesLogMapper.playerLikesCountToday(likesLog);
    }

    @LcnTransaction
    @Transactional
    @Override
    public List<PlayerLikes> getPlayerInvestLikes(String playerId) throws BusinessException {
        List<PlayerLikes> likes = playerLikesMapper.getInvestLikes(playerId);
        return likes;
    }

    @LcnTransaction
    @Transactional
    @Override
    public int getLikeCount(Integer likedId) throws BusinessException {
        PlayerLikes likes = playerLikesMapper.selectByPrimaryKey(likedId);
        Integer count = likes.getLikedGetTotal();
        return count == null ? 0 : count;
    }

    @LcnTransaction
    @Transactional
    @Override
    public void savePlayerLikesLog(PlayerLikesReq playerLikes) throws BusinessException {
        PlayerLikesLog record = new PlayerLikesLog();
        record.setCreateTime(new Date());
        record.setLikeInvestId(playerLikes.getLikedInvestId());
        record.setLikeLikedId(playerLikes.getLikedPlayerId());
        record.setLikePlayerId(playerLikes.getLikePlayerId());
        likesLogMapper.insertSelective(record);
    }

    @LcnTransaction
    @Transactional
    @Override
    public Result like(String from, String to) throws BusinessException{
        int[] ids = {5};
        List<Integer> ins = new ArrayList<>(7);

        List<InvestOrder> orders = investOrderMapper.getSuccessInvestOrdersByPlayerId(to, ids);
        orders.forEach((order) -> {
            PlayerLikesLog playerLikesLog = new PlayerLikesLog();
            playerLikesLog.setLikeLikedId(to);
            playerLikesLog.setLikePlayerId(from);
            playerLikesLog.setLikeInvestId(order.getOrderInvestId());
            int likesLogCount = likesLogMapper.investLikesCountToday(playerLikesLog);
            if (likesLogCount > 0) {

            } else {
                ins.add(order.getOrderInvestId());
            }

        });

        int investId = 0;
        if (orders.size() == 1) {
            investId = (int) ins.get(0);
        } else {
            long currentTimeMillis = System.currentTimeMillis();
            String[] mills = String.valueOf(currentTimeMillis).split("");
            int last = Integer.parseInt(mills[mills.length - 1]);
            while (last > ins.size()) {
                currentTimeMillis = System.currentTimeMillis();
                mills = String.valueOf(currentTimeMillis).split("");
                last = Integer.parseInt(mills[mills.length - 1]);
            }
            investId = ins.get(last);
        }

        PlayerLikes likesTo   = playerLikesMapper.getLikes(to);
        PlayerLikes likesFrom = playerLikesMapper.getLikes(from);
        int insertId = 0;
        if (Objects.isNull(likesTo)) {
            likesTo = new PlayerLikes();
            likesTo.setLikedId(0);
            likesTo.setLikedPlayerId(to);
            likesTo.setLikedSetTotal(0);
            //设置获取到1
            likesTo.setLikedGetTotal(1);
            likesTo.setCreateTime(new Date());
            likesTo.setUpdateTime(new Date());
            likesTo.setLikedInvestId(investId);
            playerLikesMapper.insert(likesTo);
        }

        if (Objects.isNull(likesFrom)) {
            likesFrom = new PlayerLikes();
            likesFrom.setLikedId(0);
            likesFrom.setLikedPlayerId(to);
            //设置付出1
            likesFrom.setLikedSetTotal(1);
            likesFrom.setLikedGetTotal(0);
            likesFrom.setCreateTime(new Date());
            likesFrom.setUpdateTime(new Date());
            likesFrom.setLikedInvestId(investId);
            playerLikesMapper.insert(likesFrom);
        }

        PlayerLikes likes = playerLikesMapper.getLikes(to);

        PlayerLikesLog likesLog = new PlayerLikesLog();
        likesLog.setId(0);
        likesLog.setCreateTime(new Date());
        likesLog.setUpdateTime(new Date());
        //设置为收取玩家对应的ID
        likesLog.setLikeId(likes.getLikedId());
        likesLog.setLikeInvestId(investId);

        likesLog.setLikePlayerId(from);
        likesLog.setLikeLikedId(to);

        return Result.result(true,"点赞成功", ReturnStatus.SUCCESS.getStatus(),likes);
    }

}
