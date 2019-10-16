package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.model.req.CityInvestReq;
import com.dream.city.base.model.resp.InvestResp;
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

    InvestResp selectCityInvest(CityInvestReq record);

    List<InvestResp> getInvestLsit(CityInvestReq record);

    Integer updateByPrimaryKeySelective(CityInvest record);


}