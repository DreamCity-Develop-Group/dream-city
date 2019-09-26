package com.dream.city.service;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Wvv
 */
@FeignClient(value = "city-message")
public interface CityMessageService {

    /**
     * 获取验证码
     * @return
     */
    @RequestMapping("/message/getCode")
    Result getCode(@RequestBody Message message);

    @RequestMapping("/message/checkCode")
    Result checkCode(@RequestParam("code")String code,@RequestParam("account")String account);
}
