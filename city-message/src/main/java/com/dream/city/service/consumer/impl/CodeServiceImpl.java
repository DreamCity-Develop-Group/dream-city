package com.dream.city.service.consumer.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.AuthCode;
import com.dream.city.base.model.mapper.AuthCodeMapper;
import com.dream.city.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;

/**
 * @author Wvv
 */
@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private AuthCodeMapper authCodeMapper;

    @LcnTransaction
    @Transactional
    @Override
    public boolean insertCode(String phone, String code) throws BusinessException {
        AuthCode record = new AuthCode();
        record.setCode(code);
        record.setPhone(phone);
        record.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return authCodeMapper.insertSelective(record)>0?Boolean.TRUE:Boolean.FALSE;
    }

    @LcnTransaction
    @Transactional
    @Override
    public boolean valid(String phone, String code) throws BusinessException {
        String  codeByPhone = authCodeMapper.selectByPhone(phone);
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(codeByPhone)){
            return false;
        }
        return code.equalsIgnoreCase(codeByPhone);
    }

    @LcnTransaction
    @Transactional
    @Override
    public Result updateCodeState(AuthCode code) throws BusinessException{
        boolean b = authCodeMapper.updateByPrimaryKeySelective(code)>0;

        return Result.result(b);
    }

    @LcnTransaction
    @Transactional
    @Override
    public AuthCode getAuthCodeByPhone(String phone) throws BusinessException {
        return authCodeMapper.getCodeByPhone(phone);
    }


}
