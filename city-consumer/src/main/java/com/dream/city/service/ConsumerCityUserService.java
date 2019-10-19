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
@FeignClient(value = "city-user", fallback = FallBackCityUser.class)
public interface ConsumerCityUserService {
    //city-user服务中方法的映射路径

    @RequestMapping("/user/index/{str}")
    String userIndex(@PathVariable("str")String str);

    @RequestMapping("/user/get/{id}")
    String users(@PathVariable("id")String id);
    

    /**
     * 用户登录
     */
    @RequestMapping("/user/login")
    String login(@RequestBody Message message);

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
