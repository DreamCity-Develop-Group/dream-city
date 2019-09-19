package com.dream.city.player.domain.mapper;


import com.dream.city.base.model.entity.CityInvest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CityInvestMapper {


    Integer deleteByPrimaryKey(Integer inId);

    Integer insertSelective(CityInvest record);

    CityInvest selectByPrimaryKey(CityInvest record);

    List<CityInvest> getInvestLsit(CityInvest record);

    Integer updateByPrimaryKeySelective(CityInvest record);

}