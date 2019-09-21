package com.dream.city.invest.domain.mapper;


import com.dream.city.base.model.entity.PlayerAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlayerAccountMapper {

    Integer deleteByPrimaryKey(Integer accId);

    Integer insertSelective(PlayerAccount record);

    PlayerAccount getPlayerAccount(PlayerAccount record);

    Integer updateByPrimaryKeySelective(PlayerAccount record);

}