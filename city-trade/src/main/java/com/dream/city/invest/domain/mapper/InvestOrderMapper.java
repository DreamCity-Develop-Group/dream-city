package com.dream.city.invest.domain.mapper;


import com.dream.city.base.model.entity.InvestOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InvestOrderMapper {

    Integer deleteByPrimaryKey(Integer orderId);

    InvestOrder insertSelective(InvestOrder record);

    InvestOrder selectByPrimaryKey(InvestOrder record);

    Integer updateByPrimaryKeySelective(InvestOrder record);

    List<InvestOrder> getInvestOrders(InvestOrder record);

}