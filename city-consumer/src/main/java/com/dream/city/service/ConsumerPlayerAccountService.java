package com.dream.city.service;

import com.dream.city.base.model.Message;
import com.dream.city.service.impl.FallBackCityUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Wvv
 */
@FeignClient(value = "city-auth", fallback = FallBackCityUser.class)
public interface ConsumerPlayerAccountService {

    /**
     * 用户账户添加
     */
    @RequestMapping("/auth/get/{username}")
    String createAccount(@PathVariable("username") String username);



}
