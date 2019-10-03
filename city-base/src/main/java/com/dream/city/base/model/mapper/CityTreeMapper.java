package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.CityTree;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CityTreeMapper {
    int deleteByPrimaryKey(Integer treeId);

    int insert(CityTree record);

    int insertSelective(CityTree record);

    CityTree selectByPrimaryKey(Integer treeId);

    int updateByPrimaryKeySelective(CityTree record);

    int updateByPrimaryKey(CityTree record);



    @Select("select * from city_tree where 1=1")
    @ResultMap("Tree1BaseResultMap")
    List<CityTree> getCity();
}