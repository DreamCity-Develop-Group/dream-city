package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.CityMessage;
import com.dream.city.base.model.req.MessageReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {

    Integer deleteByPrimaryKey(Long id);

    Integer insertSelective(CityMessage record);

    CityMessage selectByPrimaryKey(Long id);

    List<CityMessage> getCityMessageList(MessageReq record);

    Integer updateByPrimaryKeySelective(CityMessage record);

}