package com.dream.city.player.domain.mapper;


import com.dream.city.base.model.entity.CityInvest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CityInvestMapper {
    int deleteByPrimaryKey(Integer inId);

    int insert(CityInvest record);

    int insertSelective(CityInvest record);

    CityInvest selectByPrimaryKey(Integer inId);

    int updateByPrimaryKeySelective(CityInvest record);

    int updateByPrimaryKey(CityInvest record);
}