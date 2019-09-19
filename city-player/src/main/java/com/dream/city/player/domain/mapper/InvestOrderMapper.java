package com.dream.city.player.domain.mapper;


import com.dream.city.base.model.entity.InvestOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InvestOrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(InvestOrder record);

    int insertSelective(InvestOrder record);

    InvestOrder selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(InvestOrder record);

    int updateByPrimaryKey(InvestOrder record);
}