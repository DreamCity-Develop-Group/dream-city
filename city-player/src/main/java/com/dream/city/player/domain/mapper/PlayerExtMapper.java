package com.dream.city.player.domain.mapper;


import com.dream.city.player.domain.entity.PlayerExt;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface PlayerExtMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlayerExt record);

    int insertSelective(PlayerExt record);

    PlayerExt selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlayerExt record);

    int updateByPrimaryKey(PlayerExt record);
}