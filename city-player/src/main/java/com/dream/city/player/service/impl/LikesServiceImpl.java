package com.dream.city.player.service.impl;

import com.dream.city.player.domain.entity.PlayerLikes;
import com.dream.city.player.domain.entity.PlayerLikesLog;
import com.dream.city.player.domain.mapper.PlayerLikesLogMapper;
import com.dream.city.player.domain.mapper.PlayerLikesMapper;
import com.dream.city.player.domain.req.PlayerLikesReq;
import com.dream.city.player.service.LikesService;
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


    @Override
    @Transactional
    public int playerLike(PlayerLikesReq record) {
        Integer i = 0;
        if (record.getLikedId() == null){
            record.setCreateTime(new Date());
            i = playerLikesMapper.insertSelective(record);
        }else {
            int count = getLikeCount(record.getLikedId());
            record.setUpdateTime(new Date());
            record.setLikedInvestTotal(count + record.getLikedInvestTotal());
            i = playerLikesMapper.updateByPrimaryKeySelective(record);
        }
        if(i>0){
            savePlayerLikesLog(record);
        }
        return i == null? 0: i;
    }


    @Override
    @Transactional
    public int cancelLike(PlayerLikesReq record) {
        int count = getLikeCount(record.getLikedId());
        record.setUpdateTime(new Date());
        record.setLikedInvestTotal(count > 0? (count - 1): count);
        Integer i = playerLikesMapper.updateByPrimaryKeySelective(record);
        if(i>0){
            savePlayerLikesLog(record);
        }
        return i == null? 0: i;
    }

    @Override
    public int playerLikesCount(PlayerLikesReq record) {
        Integer likesCount = playerLikesMapper.playerLikesCount(record);
        return likesCount == null? 0: likesCount;
    }

    @Override
    public List<PlayerLikes> playerLikesList(PlayerLikesReq record) {
        return playerLikesMapper.playerLikesList(record);
    }



    private int getLikeCount(Integer likedId){
        PlayerLikes likes = playerLikesMapper.selectByPrimaryKey(likedId);
        Integer count =likes.getLikedInvestTotal();
        return count == null?0:count;
    }


    private void savePlayerLikesLog(PlayerLikesReq playerLikes){
        PlayerLikesLog record = new PlayerLikesLog();
        record.setCreateTime(new Date());
        record.setLikeInvestId(playerLikes.getLikedInvestId());
        record.setLikeLikedId(playerLikes.getLikedPlayerId());
        record.setLikePlayerId(playerLikes.getLikePlayerId());
        likesLogMapper.insertSelective(record);
    }


}
