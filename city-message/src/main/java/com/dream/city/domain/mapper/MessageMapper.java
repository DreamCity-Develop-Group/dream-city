package com.dream.city.domain.mapper;


import com.dream.city.base.model.entity.CityMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {

    Integer deleteByPrimaryKey(Long id);

    Integer insertSelective(CityMessage record);

    CityMessage selectByPrimaryKey(Long id);

    List<CityMessage> getCityMessageList(CityMessage record);

    Integer updateByPrimaryKeySelective(CityMessage record);

}