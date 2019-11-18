package com.dream.city.service.consumer;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.req.PlayerAccountReq;
import com.dream.city.base.model.resp.PlayerAccountResp;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
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



    @RequestMapping("/getPlatformAccounts")
    Result<PlayerAccountResp> getPlayerAccounts(@RequestBody PlayerAccountReq record);


    /**
     * 获取玩家账户
     * @param playerId
     * @return
     */
    @ApiOperation(value = "获取玩家账户", httpMethod = "POST", notes = "入参playerId", response = Result.class)
    @RequestMapping("/getPlayerAccount/{playerId}")
    Result<PlayerAccount> getPlayerAccount(@PathVariable("playerId") String playerId);

    /**
     * 获取玩家账户
     * @param playerId
     * @return
     */
    @ApiOperation(value = "获取玩家账户", httpMethod = "POST", notes = "入参username", response = Result.class)
    @RequestMapping("/getByPlayerId")
    PlayerAccount getPlayerAccountByPlayerId(@RequestParam("playerId")String playerId);


    /**
     * 获取玩家账户
     * @param addr
     * @return
     */
    @ApiOperation(value = "获取玩家账户", httpMethod = "POST", notes = "入参username", response = Result.class)
    @RequestMapping("/getPlayerAccountByAddr")
    PlayerAccount getPlayerAccountByAddr(@RequestParam("addr")String addr);

    /**
     * 玩家账户列表
     * @param record
     * @return
     */
    @ApiOperation(value = "玩家账户列表", httpMethod = "POST", notes = "t入参username", response = Result.class)
    @RequestMapping("/getPlayerAccountList")
    Result<List<PlayerAccountResp>> getPlayerAccountList(@RequestBody PlayerAccountReq record);


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
    Result<Integer> updatePlayerAccount(@RequestBody PlayerAccount record);



}
