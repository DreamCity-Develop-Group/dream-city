package com.dream.city.player.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.LoginLog;
import com.dream.city.base.model.mapper.LoginLogMapper;
import com.dream.city.player.service.LoginLogServcie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class LoginLogServcieImpl implements LoginLogServcie {

    private static final Logger logger = LoggerFactory.getLogger(LoginLogServcieImpl.class);

    @Autowired
    LoginLogMapper loginLogMapper;

    @LcnTransaction
    @Transactional
    @Override
    public int insertLoginLog(LoginLog record) throws BusinessException {
        int i = 0;
        try {
            record.setCreateTime(new Date());
            i = loginLogMapper.insertSelective(record);
        }catch (Exception e){
            i = 0;
            logger.error("新增登录日志异常!s",e);
        }
        return i;
    }
}
