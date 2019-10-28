package com.dream.city.controller;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.service.handler.DefaultService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "API-Consumer Main Default Page", tags = "主页数据接口")
@RestController
@Slf4j
@RequestMapping("/consumer/main")
public class DefaultController {
    @Autowired
    DefaultService defaultService;



    @ApiOperation(
            value = "主页数据接口Player",
            httpMethod = "POST",
            notes = "此接口提供游戏主页<br/>" +
                    "玩家数据及基本游戏数据<br>" +
                    "每个数据都不能缺失<hr/>",
            response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "message",
                    value = "消息主体",
                    required = true,
                    dataType = "Message",
                    paramType = "body")
    })
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success — 请求已完成"),
                    @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
                    @ApiResponse(code = 401, message = "未授权客户机访问数据"),
                    @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
                    @ApiResponse(code = 500, message = "服务器不能完成请求")
            }
    )
    @RequestMapping(value = "/default", method = RequestMethod.POST)
    public Message enterMainPage(@RequestBody Message msg) {
        try {
            return defaultService.enterMainPage(msg);
        }catch (Exception e){
            msg.setDesc(e.getMessage());
            return msg;
        }
    }

}
