package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.CityMessage;
import com.dream.city.base.model.req.MessageReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageMapper {

    Integer deleteByPrimaryKey(Long id);

    Integer insertSelective(CityMessage record);

    Integer getUnReadCount(@Param("playerId")String playerId);

    CityMessage selectByPrimaryKey(Long id);

    List<CityMessage> getCityMessageList(MessageReq record);

    Integer updateByPrimaryKeySelective(CityMessage record);

}