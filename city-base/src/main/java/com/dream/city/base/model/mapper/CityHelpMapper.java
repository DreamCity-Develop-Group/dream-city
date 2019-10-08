package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.CityHelp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CityHelpMapper {

    Integer deleteByPrimaryKey(Integer id);

    Integer insertSelective(CityHelp record);

    CityHelp selectByPrimaryKey(Integer id);

    List<CityHelp> selectCityHelpList(CityHelp record);

    Integer updateByPrimaryKeySelective(CityHelp record);


}