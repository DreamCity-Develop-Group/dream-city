package com.dream.city.service;

import com.dream.city.domain.req.UserReq;
import com.dream.city.service.impl.FallBackPlayer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Wvv
 */
@FeignClient(value = "city-player", fallback = FallBackPlayer.class)
public interface ConsumerPlayerService {

    @RequestMapping("/player/get/{playerId}")
    String getPlayer(@PathVariable("playerId") String playerId);

    @RequestMapping("/player/get/")
    String getPlayers(@RequestBody UserReq jsonReq);
    /**
     * 用户注册
     */
    @RequestMapping("/player/reg")
    String reg(@RequestBody UserReq jsonReq);

    /**
     * 用户登录
     */
    @RequestMapping("/player/login")
    String login(@RequestBody UserReq jsonReq);

    /**
     * 用户退出
     */
    @RequestMapping("/player/quit")
    String quit(@RequestParam String playerId);

    /**
     * 用户忘记密码重置
     */
    @RequestMapping("/player/resetLoginPwd")
    String resetLoginPwd(@RequestParam String playerId,@RequestParam String userpass);

    /**
     * 重置交易密码
     * @param playerId
     * @param pwshop
     * @return
     */
    @RequestMapping("/player/resetTraderPwd")
    String resetTraderPwd(@RequestParam String playerId,@RequestParam String pwshop);

}
