package com.dream.city.player.service;


import com.dream.city.base.model.entity.PlayerExt;
import org.apache.ibatis.annotations.Mapper;

public interface PlayerExtService {

    Integer deletePlayerExtById(Long id);

    Integer insertPlayerExt(PlayerExt record);

    PlayerExt getPlayerExtById(Long id);

    Integer updatePlayerExtById(PlayerExt record);

    Integer updatePlayerExtByPlayerId(PlayerExt record);

}