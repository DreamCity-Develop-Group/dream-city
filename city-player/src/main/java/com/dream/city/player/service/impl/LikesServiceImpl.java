package com.dream.city.player.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Page;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Likes;
import com.dream.city.base.model.entity.PlayerLikes;
import com.dream.city.base.model.entity.PlayerLikesLog;
import com.dream.city.base.model.mapper.LikesMapper;
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

import java.util.Date;
import java.util.List;

@Service
public class LikesServiceImpl implements LikesService {


    @Autowired
    PlayerLikesMapper playerLikesMapper;
    @Autowired
    PlayerLikesLogMapper likesLogMapper;
    @Autowired
    LikesMapper likesMapper;


    @LcnTransaction
    @Transactional
    @Override
    public int playerLike(PlayerLikesReq record) throws BusinessException {
        Integer i = 0;
        if (record.getLikedId() == null){
            record.setCreateTime(new Date());
            i = playerLikesMapper.insertSelective(record);
        }else {
            int count = getLikeCount(record.getLikedId());
            record.setUpdateTime(new Date());
            record.setLikedSetTotal(count + record.getLikedSetTotal());
            i = playerLikesMapper.updateByPrimaryKeySelective(record);
        }
        if(i>0){
            savePlayerLikesLog(record);
        }
        return i == null? 0: i;
    }


    @LcnTransaction
    @Transactional
    @Override
    public int cancelLike(PlayerLikesReq record)  throws BusinessException{
        int count = getLikeCount(record.getLikedId());
        record.setUpdateTime(new Date());
        record.setLikedSetTotal(count > 0? (count - 1): count);
        Integer i = playerLikesMapper.updateByPrimaryKeySelective(record);
        if(i>0){
            savePlayerLikesLog(record);
        }
        return i == null? 0: i;
    }

    @LcnTransaction
    @Transactional
    @Override
    public int playerLikesCount(PlayerLikesReq record) throws BusinessException {
        Integer likesCount = playerLikesMapper.playerLikesCount(record);
        return likesCount == null? 0: likesCount;
    }

    @LcnTransaction
    @Transactional
    @Override
    public PageInfo<PlayerLikesResp> playerLikesList(Page record) throws BusinessException {
        PlayerLikesReq likesReq = DataUtils.toJavaObject(record.getCondition(),PlayerLikesReq.class);
        PageHelper.startPage(record.getPageNum(),record.getPageSize(),record.isCount());
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
    public List<Likes> getPlayerInvestLikes(String playerId) throws BusinessException {
        List<Likes> likes = likesMapper.getInvestLikes(playerId);
        return likes;
    }

    @LcnTransaction
    @Transactional
    @Override
    public int getLikeCount(Integer likedId) throws BusinessException{
        PlayerLikes likes = playerLikesMapper.selectByPrimaryKey(likedId);
        Integer count =likes.getLikedGetTotal();
        return count == null?0:count;
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


}
