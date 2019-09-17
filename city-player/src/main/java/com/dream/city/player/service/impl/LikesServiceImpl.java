package com.dream.city.player.service.impl;

import com.dream.city.player.domain.entity.PlayerLikes;
import com.dream.city.player.domain.mapper.PlayerLikesMapper;
import com.dream.city.player.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LikesServiceImpl implements LikesService {


    @Autowired
    PlayerLikesMapper playerLikesMapper;



    @Override
    public int playerLike(PlayerLikes record) {
        Integer i = 0;
        if (record.getLikedId() == null){
            record.setCreateTime(new Date());
            i = playerLikesMapper.insertSelective(record);
        }else {
            record.setUpdateTime(new Date());
            i = playerLikesMapper.updateByPrimaryKeySelective(record);
        }
        return i == null? 0: i;
    }

    @Override
    public int cancelLike(PlayerLikes record) {
        int count = record.getLikedInvestTotal()==null? 0: record.getLikedInvestTotal();
        record.setUpdateTime(new Date());
        record.setLikedInvestTotal(count > 0? (count - 1): count);
        Integer i = playerLikesMapper.updateByPrimaryKeySelective(record);
        return i == null? 0: i;
    }

    @Override
    public int playerLikesCount(PlayerLikes record) {
        Integer likesCount = playerLikesMapper.playerLikesCount(record);
        return likesCount == null? 0: likesCount;
    }

    @Override
    public List<PlayerLikes> playerLikesList(PlayerLikes record) {
        return playerLikesMapper.playerLikesList(record);
    }
}
