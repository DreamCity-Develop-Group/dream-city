package com.dream.city.service;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 玩家账户
 */
@FeignClient(value = "city-trade")
@RequestMapping("/invest")
public interface ConsumerAccountService {

    /**
     * 新增玩家账户
     * @param record
     * @return
     */
    @RequestMapping("/createPlayerAccount")
    Result createPlayerAccount(@RequestBody PlayerAccount record);

    /**
     * 获取玩家账户
     * @param record
     * @return
     */
    @RequestMapping("/getPlayerAccount")
    Result getPlayerAccount(@RequestBody PlayerAccount record);

    /**
     * 玩家账户列表
     * @param record
     * @return
     */
    @RequestMapping("/getPlayerAccountList")
    Result getPlayerAccountList(@RequestBody PlayerAccount record);

    /**
     * 更新玩家账户
     * @param record
     * @return
     */
    @RequestMapping("/updatePlayerAccount")
    Result updatePlayerAccount(@RequestBody PlayerAccount record);

}
