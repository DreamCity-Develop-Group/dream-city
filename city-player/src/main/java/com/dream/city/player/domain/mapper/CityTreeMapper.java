package com.dream.city.player.domain.mapper;


import com.dream.city.base.model.entity.CityTree;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CityTreeMapper {
    int deleteByPrimaryKey(Integer treeId);

    int insert(CityTree record);

    int insertSelective(CityTree record);

    CityTree selectByPrimaryKey(Integer treeId);

    int updateByPrimaryKeySelective(CityTree record);

    int updateByPrimaryKey(CityTree record);
}