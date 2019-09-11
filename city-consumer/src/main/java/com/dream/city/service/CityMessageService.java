package com.dream.city.service;

import com.dream.city.base.model.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
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
    @RequestMapping("/message/getCode")
    Message getCode(@RequestBody Message message);

    @RequestMapping("/message/valiCode")
    Message validCode(@RequestBody Message message);
}
