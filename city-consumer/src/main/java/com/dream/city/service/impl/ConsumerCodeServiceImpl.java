package com.dream.city.service.impl;

import com.dream.city.base.model.Result;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.ConsumerCodeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerCodeServiceImpl implements ConsumerCodeService {
    @Autowired
    RedisUtils redisUtils;

    @Override
    public Result checkCode(String code, String phone){
        boolean res = false;
        String msg = "";
        int retCode = 0;

        String redisCode = redisUtils.getStr("Code_"+phone);

        if (!StringUtils.isBlank(redisCode)){

            if(redisCode.equals(code)){
                msg = "验证成功";
                retCode = 200;
            }else {
                msg = "验证码不正确";
                retCode = 500;
            }

        }else {
            msg = "验证码已过期";
            retCode = 501;
        }

        return new Result(retCode,msg);
    }
}
