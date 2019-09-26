package com.dream.city.player.domain.mapper;


import com.dream.city.base.model.entity.PlayerExt;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlayerExtMapper {

    Integer deleteByPrimaryKey(Long id);

    Integer insertSelective(PlayerExt record);

    PlayerExt selectByPrimaryKey(Long id);

    Integer updateByPrimaryKeySelective(PlayerExt record);

    Integer updateByPlayerIdSelective(PlayerExt record);

}