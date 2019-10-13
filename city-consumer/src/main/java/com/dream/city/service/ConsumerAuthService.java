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
public interface ConsumerAuthService {

    /**
     * 用户登录
     */
    @RequestMapping("/auth/get/{username}")
    String getAuth(@PathVariable("username")String username);

    /**
     * 用户退出
     */
    @RequestMapping("/user/quit")
    String quit(@RequestBody Message message);

    /**
     * 用户忘记密码重置
     */
    @RequestMapping("/user/reset")
    String reset(@RequestBody Message message);
}
