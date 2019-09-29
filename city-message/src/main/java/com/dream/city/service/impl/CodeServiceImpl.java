package com.dream.city.service.impl;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.AuthCode;
import com.dream.city.domain.mapper.AuthCodeMapper;
import com.dream.city.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Wvv
 */
@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private AuthCodeMapper authCodeMapper;


    @Override
    public boolean insertCode(String phone, String code) {
        AuthCode record = new AuthCode();
        record.setCode(code);
        record.setPhone(phone);
        record.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return authCodeMapper.insertSelective(record)>0?Boolean.TRUE:Boolean.FALSE;
    }

    @Override
    public boolean valid(String phone, String code) {
        String  codeByPhone = authCodeMapper.selectByPhone(phone);
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(codeByPhone)){
            return false;
        }
        return code.equalsIgnoreCase(codeByPhone);
    }

    @Override
    public Result updateCodeState(AuthCode code){
        boolean b = authCodeMapper.updateByPrimaryKeySelective(code)>0;

        return Result.result(b);
    }

    @Override
    public AuthCode getAuthCodeByPhone(String phone) {
        return authCodeMapper.getCodeByPhone(phone);
    }


}
