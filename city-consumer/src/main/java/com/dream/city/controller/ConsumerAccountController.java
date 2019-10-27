package com.dream.city.controller;

import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.service.routes.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "获取玩家账户", description = "获取玩家账户信息")
@RestController
@RequestMapping("/consumer")
public class ConsumerAccountController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AccountService accountService;


    /**
     * 获取玩家账户
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "获取玩家账户", httpMethod = "POST", notes = "入参username", response = Message.class)
    @RequestMapping("/account/getPlayerAccount")
    public Message getPlayerAccount(@RequestBody Message msg) {
        try {
            return accountService.getPlayerAccount(msg);
        } catch (BusinessException e) {
            //throw e;
            return Message.generateMessage(msg, Result.result(false));
        }
    }

}
