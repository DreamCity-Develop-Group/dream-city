package com.dream.city.player.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.CityGlobal;
import com.dream.city.base.model.Result;
import com.dream.city.player.domain.entity.LoginLog;
import com.dream.city.player.domain.entity.Player;
import com.dream.city.player.service.LoginLogServcie;
import com.dream.city.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 玩家
 * @author Wvv
 */
@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    PlayerService playerService;
    @Autowired
    LoginLogServcie loginLogServcie;

    /**
     * 用户注册
     * @param jsonReq(用户名，用户密码)
     * @return
     */
    @RequestMapping("/reg")
    public Result reg(@RequestParam("json") String jsonReq){
        Result<JSONObject> result = new Result<>();
        if (StringUtils.isEmpty(jsonReq) || (!StringUtils.isEmpty(jsonReq) && "{}".equals(jsonReq))){
            result.setSuccess(Boolean.FALSE);
            result.setCode(CityGlobal.ResultCode.fail.getStatus());
            return result;
        }

        Map<String,String> player = JSON.parseObject(jsonReq,Map.class);
        String playerName = player.get("username");
        String playerPass = player.get("userpass");
        String code = player.get("code");
        String nick = player.get("nick");
        String invite = player.get("invite");

        String tip = "";
        if(StringUtils.isEmpty(playerName) || StringUtils.isEmpty(playerPass)){
            tip=  "用户名或密码为空";
        }
        if(StringUtils.isEmpty(code)){
            tip = "短信验证码为空";
        }
        // 邀请码：为选填项，可填写可不填；
        /*if(StringUtils.isEmpty(invite)){
            tip = "邀请码为空";
        }*/
        if (StringUtils.isEmpty(nick)){
            nick = playerName;
        }

        if (StringUtils.isEmpty(tip)){
            Player playerSave = new Player();
            playerSave.setUsername(playerName);
            playerSave.setUserpass(playerPass);
            playerSave.setInvited(invite);
            playerSave.setNick(nick);
            boolean ret = playerService.save(playerSave);
            if (ret){
                tip = "注册成功";
                result.setSuccess(Boolean.TRUE);
                result.setCode(CityGlobal.ResultCode.success.getStatus());
            }
        }

        result.setMsg(tip);
        return result;
    }

    /**
     * 用户登录
     * @param jsonReq
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public Result login(@RequestParam String jsonReq){
        Player player = JSON.parseObject(jsonReq,Player.class);
        Result result = new Result();
        result.setCode(CityGlobal.ResultCode.fail.getStatus());

        String username = player.getUsername();
        String userpass = player.getUserpass();
        String playerId = null;

        StringBuilder tip = new StringBuilder();
        if (StringUtils.isEmpty(username)){
            tip.append(CityGlobal.Constant.USER_NAME_NULL);
        }
        if (StringUtils.isEmpty(userpass)){
            tip.append(CityGlobal.Constant.USER_PWD_NULL);
        }
        boolean login = Boolean.TRUE;
        if (!StringUtils.isEmpty(tip)){
            result.setMsg(tip.toString());
        }else {
            // 用户是否存在
            Player playerExit = playerService.getPlayer(player);
            if (playerExit == null){
                result.setMsg(CityGlobal.Constant.USER_NOT_EXIT);
                tip.append(CityGlobal.Constant.USER_NOT_EXIT);
            }else {
                playerId = playerExit.getPlayerId();
                // 用户名
                if (playerExit.getUsername().equals(username)){
                    login = Boolean.FALSE;
                }
                // 密码
                if (playerExit.getUserpass().equals(userpass)){
                    login = Boolean.FALSE;
                }
                if (login) {
                    tip.append(CityGlobal.Constant.LOGIN_SUCCESS);
                    result.setMsg(CityGlobal.Constant.LOGIN_SUCCESS);
                    result.setSuccess(login);
                    result.setCode(CityGlobal.ResultCode.success.getStatus());
                }
            }
        }

        // 登录记录
        LoginLog record = new LoginLog();
        record.setDescr(tip.toString());
        record.setPlayerId(playerId);
        record.setType("login");
        loginLogServcie.insertLoginLog(record);

        return result;
    }


    /**
     * 用户退出
     * 注：在city-consumer退出
     * @param playerId
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/quit")
    public Result quit(@RequestParam(value = "playerId")String playerId){
        LoginLog record = new LoginLog();
        record.setDescr("用户退出");
        record.setPlayerId(playerId);
        record.setType("quit");
        loginLogServcie.insertLoginLog(record);
        return null;
    }

    /**
     * 重置登录密码
     * @param playerId
     * @param userpass
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/resetLoginPwd")
    public Result resetLoginPwd(@RequestParam(value = "playerId")String playerId,
                                @RequestParam(value = "userpass")String userpass){

        boolean b = playerService.resetLoginPwd(playerId, userpass);
        Result result = null;
        if (b){
            result = new Result("重置交易密码成功", CityGlobal.ResultCode.success.getStatus());
        }else {
            result = new Result("重置交易密码失败", CityGlobal.ResultCode.fail.getStatus());
        }
        return result;
    }


    /**
     * 重置交易密码
     * @param playerId
     * @param pwshop
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/resetTraderPwd")
    public Result resetTraderPwd(@RequestParam(value = "playerId")String playerId,
                                @RequestParam(value = "pwshop")String pwshop){
        boolean b = playerService.resetTraderPwd(playerId, pwshop);
        Result result = null;
        if (b){
            result = new Result("重置交易密码成功", CityGlobal.ResultCode.success.getStatus());
        }else {
            result = new Result("重置交易密码失败", CityGlobal.ResultCode.fail.getStatus());
        }
        return result;
    }

    /**
     * 玩家
     * @param playerId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/get/{playerId}")
    public Result getPlayer(@PathVariable(value = "playerId")String playerId){
        Player player = playerService.getPlayerById(playerId);
        Result result = null;
        if (player != null){
            result = new Result(CityGlobal.ResultCode.success.getStatus(),player);
        }else {
            result = new Result(CityGlobal.ResultCode.fail.getStatus(),player);
        }
        return result;
    }

    /**
     * 玩家列表
     * @param jsonReq
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public Result getPlayers(@RequestParam String jsonReq){
        Player player = JSON.parseObject(jsonReq,Player.class);
        List<Player> players = playerService.getPlayers(player);
        Result result = null;
        if (!CollectionUtils.isEmpty(players)){
            result = new Result(CityGlobal.ResultCode.success.getStatus(),players);
        }else {
            result = new Result(CityGlobal.ResultCode.fail.getStatus(),players);
        }
        return result;
    }


}
