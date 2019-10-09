package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.LoginLog;
import com.dream.city.base.model.req.LoginLogReq;
import com.dream.city.base.model.resp.LoginLogResp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginLogMapper {

    int deleteByPrimaryKey(Long id);

    int insertSelective(LoginLog record);

    LoginLogResp selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LoginLog record);

    List<LoginLogResp> getLoginLogList(LoginLogReq record);

}