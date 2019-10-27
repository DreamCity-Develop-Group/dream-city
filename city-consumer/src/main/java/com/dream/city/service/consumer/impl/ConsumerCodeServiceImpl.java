package com.dream.city.service.consumer.impl;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.consumer.ConsumerCodeService;
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
                retCode = ReturnStatus.SUCCESS.getStatus();
            }else {
                msg = "验证码不正确";
                retCode = ReturnStatus.ERROR_CODE.getStatus();
            }

        }else {
            msg = "验证码已过期";
            retCode = ReturnStatus.CODE_EXPIRED.getStatus();
        }

        return new Result(retCode,msg);
    }
}
