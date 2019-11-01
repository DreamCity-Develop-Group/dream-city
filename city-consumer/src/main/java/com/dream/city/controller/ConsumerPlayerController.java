package com.dream.city.controller;

import com.dream.city.base.model.*;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.service.handler.PlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 玩家Controller
 *
 * @author Wvv
 */
@Slf4j
@Api(value = "玩家", description = "玩家")
@RestController
@RequestMapping("/consumer/player")
public class ConsumerPlayerController {
    @Autowired
    PlayerService playerService;


    /**
     * 修改玩家头像
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "修改玩家头像", httpMethod = "POST", notes = "修改玩家头像，参数：username,imgUrl", response = Message.class)
    @RequestMapping("/updatePlayerHeadImg")
    public Message updatePlayerHeadImg(@RequestBody Message msg) {
        try {
            return playerService.updatePlayerHeadImg(msg);
        }catch (Exception e){
            //throw new BusinessException("setplayertree");
            return msg;
        }
    }


    @RequestMapping("/searchFriend")
    @ApiOperation(value = "换一批广场玩家列表", httpMethod = "POST", notes = "换一批广场玩家列表", response = Message.class)
    public Message searchfriend(@RequestBody Message msg) {
        try {
            return playerService.searchfriend(msg);
        }catch (Exception e){
            //throw new BusinessException("setplayertree");
            return msg;
        }
    }

    @RequestMapping("/setTradePass")
    @ApiOperation(value = "设置交易密码", httpMethod = "POST", notes = "设置交易密码", response = Message.class)
    public Message setTradePass(@RequestBody Message msg) {
        try {
            return playerService.setTradePass(msg);
        }catch (Exception e){
            //throw new BusinessException("setplayertree");
            return msg;
        }
    }



    /**
     * 获取认证码
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "获取认证码", httpMethod = "POST", notes = "获取认证码", response = Message.class)
    @RequestMapping("/getCode")
    public Message getCode(@RequestBody Message msg) {
        try {
            return playerService.getCode(msg);
        }catch (Exception e){
            //throw new BusinessException("setplayertree");
            return msg;
        }
    }


    /**
     * 获取当前用户
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "获取当前用户", httpMethod = "POST", notes = "获取当前用户", response = Message.class)
    @RequestMapping("/get/user")
    public Message getUser(@RequestBody Message msg) {
        try {
            return playerService.getUser(msg);
        }catch (Exception e){
            //throw new BusinessException("setplayertree");
            return msg;
        }
    }


    /**
     * 广场玩家列表
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "广场玩家列表", httpMethod = "POST", notes = "广场玩家列表", response = Message.class)
    @RequestMapping("/squareFriends")
    public Message squareFriends(@RequestBody Message msg) {
        try {
            return playerService.squareFriends(msg);
        }catch (Exception e){
            //throw new BusinessException("setplayertree");
            return msg;
        }
    }


    /**
     * 忘记密码
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "忘记密码", httpMethod = "POST", notes = "忘记密码", response = Message.class)
    @RequestMapping("/pwforget")
    public Message pwforget(@RequestBody Message msg) {
        try {
            return playerService.pwforget(msg);
        }catch (Exception e){
            //throw new BusinessException("setplayertree");
            return msg;
        }
    }

    /**
     * 修改密码
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "修改密码", httpMethod = "POST", notes = "修改密码", response = Message.class)
    @RequestMapping("/resetLoginPass")
    public Message resetLoginPass(@RequestBody Message msg) {
        try {
            return playerService.resetLoginPass(msg);
        }catch (Exception e){
            //throw new BusinessException("setplayertree");
            return msg;
        }
    }

    /**
     * 设置、修改交易密码
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "设置、修改交易密码", httpMethod = "POST", notes = "修改交易密码，没有交易密码的设置交易密码，有交易密码的修改交易密码", response = Message.class)
    @RequestMapping("/resetTradePass")
    public Message resetTradePass(@RequestBody Message msg) {
        try {
            return playerService.resetTradePass(msg);
        }catch (Exception e){
            //throw new BusinessException("setplayertree");
            return msg;
        }
    }


    /**
     * 用户注册
     *
     * @param  : {
     *                source: clientId,
     *                target: server,
     *                desc:"",
     *                createtime:2019-09-09,
     *                data:{
     *                type: reg,
     *                model: consumer,
     *                data: {
     *                username: wvv,
     *                password: 123456,
     *                nick: wvv1,
     *                invite: 2qwef21,
     *                code: 324512
     *                }
     *                }
     *                <p>
     *                }
     * @return
     */
    @ApiOperation(value = "用户注册", httpMethod = "POST", notes = "用户注册", response = Message.class)
    @RequestMapping("/reg")
    public Message reg(@RequestBody Message msg) {
        try {
            return playerService.reg(msg);
        }catch (Exception e){
            //throw new BusinessException("setplayertree");
            return msg;
        }
    }




    /**
     * 密码登录
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "密码登录", httpMethod = "POST", notes = "密码登录", response = Message.class)
    @RequestMapping("/login")
    public Message login(@RequestBody Message msg) {
        try {
            return playerService.login(msg);
        }catch (Exception e){
            //throw new BusinessException("setplayertree");
            msg.getData().setCode(ReturnStatus.FAILED.getStatus());
            return msg;
        }
    }



    /**
     * 验证码登录
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "验证码登录", httpMethod = "POST", notes = "验证码登录", response = Message.class)
    @RequestMapping("/codeLogin")
    public Message codeLogin(@RequestBody Message msg) {
        try {
            return playerService.codeLogin(msg);
        }catch (Exception e){
            //throw new BusinessException("setplayertree");
            return msg;
        }
    }


    /**
     * 登出
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "登出", httpMethod = "POST", notes = "登出", response = Message.class)
    @RequestMapping("/exit")
    public Message exit(@RequestBody Message msg) {
        try {
            return playerService.exit(msg);
        }catch (Exception e){
            //throw new BusinessException("setplayertree");
            return msg;
        }
    }





    @RequestMapping("/readMessage")
    public Message readMessage(@RequestBody Message msg) {
        try {
            return playerService.readMessage(msg);
        }catch (Exception e){
            //throw new BusinessException("setplayertree");
            return msg;
        }
    }


    @RequestMapping("/getMessageList")
    public Message getMessageList(@RequestBody Message msg) {
        try {
            return playerService.getMessageList(msg);
        }catch (Exception e){
            //throw new BusinessException("setplayertree");
            return msg;
        }
    }

}