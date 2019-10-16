package com.dream.city.invest.controller;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.model.req.PlayerAccountReq;
import com.dream.city.invest.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 玩家账户
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountService accountService;


    /**
     * 新增玩家账户
     * @param record
     * @return
     */
    @RequestMapping("/createPlayerAccount")
    public Result<Integer> createPlayerAccount(@RequestBody PlayerAccount record){
        logger.info("新增玩家账户，{}", record);

        int i = 0;
        String desc = "新增玩家账户成功";
        boolean b = Boolean.TRUE;
        try {
            i = accountService.createPlayerAccount(record);
        }catch (Exception e){
            desc = "新增玩家账户失败";
            b = Boolean.FALSE;
            logger.error("新增玩家账户异常",e);
        }
        Result<Integer> result = new Result<>(b,desc,i);
        return result;
    }

    /**
     * 获取玩家账户
     * @param playerId
     * @return
     */
    @RequestMapping("/getPlayerAccount/{playerId}")
    public Result<PlayerAccount> getPlayerAccount(@PathVariable("playerId") String playerId){
        logger.info("获取玩家账户，{}", playerId);
        String desc = "获取玩家账户成功";
        PlayerAccount account = null;
        boolean b = Boolean.TRUE;
        try {
            account = accountService.getPlayerAccount(playerId);
        }catch (Exception e){
            desc = "获取玩家账户失败";
            b = Boolean.FALSE;
            logger.error("获取玩家账户异常",e);
        }
        Result<PlayerAccount> result = new Result<>(b,desc,account);
        return result;
    }

    /**
     * 获取平台账户
     * @param record
     * @return
     */
    /*@RequestMapping("/getPlatformAccounts")
    public Result<PlayerAccount> getPlayerAccount(@RequestBody PlayerAccountReq record){
        logger.info("获取平台账户，{}", record);
        String desc = "获取平台账户成功";
        PlayerAccount account = null;
        boolean b = Boolean.TRUE;
        try {
            List<PlayerAccount> accountList = accountService.getPlatformAccounts(record);
            if (!CollectionUtils.isEmpty(accountList)){
                account = accountList.get(0);
            }
        }catch (Exception e){
            desc = "获取玩平台户失败";
            b = Boolean.FALSE;
            logger.error("获取平台账户异常",e);
        }
        Result<PlayerAccount> result = new Result<>(b,desc,account);
        return result;
    }*/

    /**
     * 玩家账户列表
     * @param record
     * @return
     */
    @RequestMapping("/getPlayerAccountList")
    public Result<List<PlayerAccount>> getPlayerAccountList(@RequestBody PlayerAccount record){
        logger.info("玩家账户列表，{}", record);
        String desc = "玩家账户列表成功";
        List<PlayerAccount> accounts = null;
        boolean b = Boolean.TRUE;
        try {
            accounts = accountService.getPlayerAccountList(record);
        }catch (Exception e){
            desc = "玩家账户列表失败";
            b = Boolean.FALSE;
            logger.error("玩家账户列表异常",e);
        }
        Result result = new Result<>(b,desc,accounts);
        return result;
    }

    /**
     * 更新玩家账户
     * @param record
     * @return
     */
    @RequestMapping("/updatePlayerAccount")
    public Result<Integer> updatePlayerAccount(@RequestBody PlayerAccount record){
        logger.info("更新玩家账户，{}", record);

        int i = 0;
        String desc = "更新玩家账户成功";
        boolean b = Boolean.TRUE;
        try {
            i = accountService.updatePlayerAccountById(record);
        }catch (Exception e){
            desc = "更新玩家账户失败";
            b = Boolean.FALSE;
            logger.error("更新玩家账户异常",e);
        }
        if (i > 0){
            return new Result<>(b,desc, ReturnStatus.SUCCESS.getStatus(),i);
        }
        return new Result<>(b,desc,ReturnStatus.ERROR.getStatus(),i);
    }




    @RequestMapping("/getByPlayerId")
    public PlayerAccount getByPlayerId(@RequestParam("playerId")String playerId){
        return accountService.getPlayerAccountByPlayerId(playerId);
    }
}
