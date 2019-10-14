package com.dream.city.service;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerAccount;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 玩家账户
 */
@FeignClient(value = "city-trade")
@RequestMapping("/account")
public interface ConsumerAccountService {


    /**
     * 获取玩家账户
     * @param record
     * @return
     */
    @ApiOperation(value = "获取玩家账户", httpMethod = "POST", notes = "t入参username", response = Result.class)
    @RequestMapping("/getPlayerAccount")
    Result getPlayerAccount(@RequestBody PlayerAccount record);
    /**
     * 获取玩家账户
     * @param playerId
     * @return
     */
    @ApiOperation(value = "获取玩家账户", httpMethod = "POST", notes = "t入参username", response = Result.class)
    @RequestMapping("/getByPlayerId")
    PlayerAccount getPlayerAccountByPlayerId(@RequestParam("playerId")String playerId);
    /**
     * 玩家账户列表
     * @param record
     * @return
     */
    @ApiOperation(value = "玩家账户列表", httpMethod = "POST", notes = "t入参username", response = Result.class)
    @RequestMapping("/getPlayerAccountList")
    Result getPlayerAccountList(@RequestBody PlayerAccount record);

    /**
     * 新增玩家账户
     * @param record
     * @return
     */
    /*@ApiOperation(value = "获取玩家账户", httpMethod = "POST", notes = "t入参username", response = Message.class)
    @RequestMapping("/createPlayerAccount")
    Result createPlayerAccount(@RequestBody PlayerAccount record);*/

    /**
     * 更新玩家账户
     * @param record
     * @return
     */
    @ApiOperation(value = "更新玩家账户", httpMethod = "POST", notes = "t入参username", response = Result.class)
    @RequestMapping("/updatePlayerAccount")
    Result updatePlayerAccount(@RequestBody PlayerAccount record);
}
