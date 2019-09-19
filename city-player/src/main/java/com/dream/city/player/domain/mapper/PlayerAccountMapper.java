package com.dream.city.player.domain.mapper;


import com.dream.city.player.domain.entity.PlayerAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlayerAccountMapper {
    int deleteByPrimaryKey(Integer accId);

    int insert(PlayerAccount record);

    int insertSelective(PlayerAccount record);

    PlayerAccount selectByPrimaryKey(Integer accId);

    int updateByPrimaryKeySelective(PlayerAccount record);

    int updateByPrimaryKey(PlayerAccount record);
}