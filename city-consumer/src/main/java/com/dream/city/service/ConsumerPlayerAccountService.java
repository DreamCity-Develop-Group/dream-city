package com.dream.city.service;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.service.impl.FallBackCityUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Wvv
 */
@FeignClient(value = "city-tree", fallback = FallBackCityUser.class)
public interface ConsumerPlayerAccountService {

    /**
     * 用户账户添加
     */
    @RequestMapping("/tree/get/{username}")
    String createAccount(@PathVariable("playerId") String playerId,@RequestParam("address")String address);


    /*@RequestMapping("/tree/get/{username}")
    String createAccount(@PathVariable("username") String username);*/

    @RequestMapping("/tree/get/account")
    Result getPlayerAccount(@RequestParam("playerId")String playerId);
}
