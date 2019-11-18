package com.dream.city.service.handler;

import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;

import java.net.URISyntaxException;

public interface PlayerService {

    /**
     * 修改玩家头像
     *
     * @param msg
     * @return
     */
    Message updatePlayerHeadImg(Message msg) throws BusinessException;


    Message searchfriend(Message msg) throws BusinessException;

    Message setTradePass(Message msg) throws BusinessException;


    /**
     * 获取认证码
     *
     * @param msg
     * @return
     */
    Message getCode(Message msg) throws BusinessException;


    /**
     * 获取当前用户
     *
     * @param msg
     * @return
     */
    Message getUser(Message msg) throws BusinessException;


    /**
     * 广场玩家列表
     *
     * @param msg
     * @return
     */
    Message squareFriends(Message msg) throws BusinessException;


    /**
     * 忘记密码
     *
     * @param msg
     * @return
     */
    Message pwforget(Message msg) throws BusinessException;

    /**
     * 修改密码
     *
     * @param msg
     * @return
     */
    Message resetLoginPass(Message msg) throws BusinessException;

    /**
     * 设置、修改交易密码
     *
     * @param msg
     * @return
     */
    Message resetTradePass(Message msg) throws BusinessException;

    /**
     * 用户注册
     *
     * @param message : {
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
    Message reg(Message message) throws URISyntaxException;


    /**
     * 密码登录
     *
     * @param msg
     * @return
     */
    Message login(Message msg) throws BusinessException;


    /**
     * 验证码登录
     *
     * @param msg
     * @return
     */
    Message codeLogin(Message msg) throws BusinessException;


    /**
     * 登出
     *
     * @param msg
     * @return
     */
    Message exit(Message msg) throws BusinessException;


    Message readMessage(Message msg) throws BusinessException;


    Message getMessageList(Message msg) throws BusinessException;

}
