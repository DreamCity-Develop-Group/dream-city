package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.CityInvest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Wvv
 */
@Mapper
public interface CityInvestMapper {


    Integer deleteByPrimaryKey(Integer inId);

    Integer insertSelective(CityInvest record);

    CityInvest selectByPrimaryKey(@Param("inId") Integer inId);

    CityInvest selectCityInvest(CityInvest record);

    List<CityInvest> getInvestLsit(CityInvest record);

    Integer updateByPrimaryKeySelective(CityInvest record);


}