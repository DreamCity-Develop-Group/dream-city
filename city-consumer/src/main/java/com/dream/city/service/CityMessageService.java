package com.dream.city.service;

import com.dream.city.domain.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Wvv
 */
@FeignClient(value = "city-message")
public interface CityMessageService {

    /**
     * 获取验证码
     * @return
     */
    @RequestMapping("/message/get/code")
    public Object getCode();

    @RequestMapping("/message/valid")
    public String validCode();
}
