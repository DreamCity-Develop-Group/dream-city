package com.dream.city.player.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.CityGlobal;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.req.UserReq;
import com.dream.city.base.utils.RedisKeys;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.player.domain.entity.LoginLog;
import com.dream.city.player.domain.entity.Player;
import com.dream.city.player.service.LoginLogServcie;
import com.dream.city.player.service.PlayerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
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
    @Autowired
    RedisUtils redisUtils;


    /**
     * 用户注册
     * @param jsonReq(用户名，用户密码)
     * @return
     */
    @RequestMapping("/reg")
    public Result reg(@RequestParam("json") String jsonReq){
        Result<JSONObject> result = new Result<>();
        if (StringUtils.isBlank(jsonReq) || (StringUtils.isNotBlank(jsonReq) && "{}".equals(jsonReq))){
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
        if(StringUtils.isBlank(playerName) || StringUtils.isBlank(playerPass)){
            tip=  "用户名或密码为空";
        }
        // 用户是否存在
        Player playerExit = playerService.getPlayerByName(playerName,null);
        if (playerExit != null){
            tip = CityGlobal.Constant.REG_USER_EXIT + ",请直接登录！";
        }

        /*if(StringUtils.isBlank(code)){
            tip = "短信验证码为空";
        }*/
        // 邀请码：为选填项，可填写可不填；
        /*if(StringUtils.isBlank(invite)){
            tip = "邀请码为空";
        }*/
        if (StringUtils.isBlank(nick)){
            nick = playerName;
        }

        if (StringUtils.isBlank(tip)){
            Player playerSave = new Player();
            playerSave.setPlayerName(playerName);
            playerSave.setPlayerPass(playerPass);
            playerSave.setPlayerInvite(invite);
            playerSave.setPlayerNick(nick);
            boolean ret = playerService.save(playerSave);
            if (ret){
                tip = "注册成功";
                result.setSuccess(Boolean.TRUE);
                result.setCode(CityGlobal.ResultCode.success.getStatus());

                // 登录成功保存redis
                loginToRedis(playerSave);
            }
        }

        result.setMsg(tip);
        return result;
    }

    /**
     * 用户密码登录
     * @param jsonReq
     * @return
     */
    @RequestMapping("/pwlogoin")
    public Result pwLogoin(@RequestParam("json") String jsonReq){
        Map map = JSON.parseObject(jsonReq,Map.class);
        Result result = new Result();
        result.setCode(CityGlobal.ResultCode.fail.getStatus());

        String username = (String)map.get("username");
        String userpass = (String)map.get("userpass");
        String playerId = null;

        StringBuilder tip = new StringBuilder();
        if (StringUtils.isBlank(username)){
            tip.append(CityGlobal.Constant.USER_NAME_NULL);
        }
        if (StringUtils.isBlank(userpass)){
            tip.append(CityGlobal.Constant.USER_PWD_NULL);
        }
        if (StringUtils.isNotBlank(tip.toString())){
            result.setMsg(tip.toString());
        }else {
            // 用户是否存在
            Player playerExists= playerService.getPlayerByName(username,null);
            if (playerExists == null){
                result.setMsg(CityGlobal.Constant.USER_NOT_EXIT);
                tip.append(CityGlobal.Constant.USER_NOT_EXIT);
            }else {
                playerId = playerExists.getPlayerId();
                // 用户名
                if (!playerExists.getPlayerName().equalsIgnoreCase(username)){
                    tip.append(CityGlobal.Constant.USER_NOT_EXIT);
                }
                // 密码
                if (!playerExists.getPlayerPass().equals(userpass)){
                    tip.append(CityGlobal.Constant.USER_PWD_ERROR);
                }
                if (StringUtils.isBlank(tip.toString())) {
                    tip.append(CityGlobal.Constant.LOGIN_SUCCESS);
                    result.setCode(CityGlobal.ResultCode.success.getStatus());
                    result.setSuccess(Boolean.TRUE);

                    // 登录成功保存redis
                    loginToRedis(playerExists);
                }
            }
        }
        result.setMsg(tip.toString());

        // 登录记录
        LoginLog record = new LoginLog();
        record.setDescr(tip.toString());
        record.setPlayerId(playerId);
        record.setType("login");
        loginLogServcie.insertLoginLog(record);

        return result;
    }

    /**
     * 登录成功保存redis
     * @param player
     */
    private void loginToRedis(Player player){
        String key = RedisKeys.CURRENT_USER + player.getPlayerName();
        if (redisUtils.hasKey(key)){
            redisUtils.del(key);
        }

        redisUtils.set(RedisKeys.CURRENT_USER + player.getPlayerName(),
                JSON.toJSONString(player));

        redisUtils.incr(RedisKeys.CURRENT_LOGIN_USER_COUNT);
    }

    /**
     * 验证码登录
     * @param jsonReq
     * @return
     */
    @RequestMapping("/codelogoin")
    public Result codeLogoin(@RequestParam("json") String jsonReq){
        Map map = JSON.parseObject(jsonReq,Map.class);
        Result result = new Result();
        result.setCode(CityGlobal.ResultCode.fail.getStatus());

        String username = (String)map.get("username");
        String playerId = null;

        StringBuilder tip = new StringBuilder();
        if (StringUtils.isBlank(username)){
            tip.append(CityGlobal.Constant.USER_NAME_NULL);
        }
        boolean login = Boolean.TRUE;
        if (StringUtils.isNotBlank(tip.toString())){
            result.setMsg(tip.toString());
        }else {
            // 用户是否存在
            Player playerExists = playerService.getPlayerByName(username,null);
            if (playerExists == null){
                result.setMsg(CityGlobal.Constant.USER_NOT_EXIT);
                tip.append(CityGlobal.Constant.USER_NOT_EXIT);
            }else {
                playerId = playerExists.getPlayerId();
                // 用户名
                if (!playerExists.getPlayerName().equalsIgnoreCase(username)){
                    login = Boolean.FALSE;
                }
                if (login) {
                    tip.append(CityGlobal.Constant.LOGIN_SUCCESS);
                    result.setMsg(tip.toString());
                    result.setSuccess(login);
                    result.setCode(CityGlobal.ResultCode.success.getStatus());

                    // 登录成功保存redis
                    loginToRedis(playerExists);
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
    @RequestMapping("/quit")
    public Result quit(@RequestParam("playerId")String playerId){
        Result result = new Result(Boolean.TRUE,"退出成功",CityGlobal.ResultCode.success.getStatus());
        // 用户是否存在
        Player player = new Player();
        player.setPlayerId(playerId);
        Player playerExit = playerService.getPlayer(player);
        if (playerExit == null){
            return result;
        }

        LoginLog record = new LoginLog();
        record.setDescr("用户退出");
        record.setPlayerId(playerId);
        record.setType("quit");
        loginLogServcie.insertLoginLog(record);

        if (redisUtils.hasKey(RedisKeys.CURRENT_USER + playerExit.getPlayerName())) {
            redisUtils.del(RedisKeys.CURRENT_USER + playerExit.getPlayerName());
            redisUtils.decr(RedisKeys.CURRENT_LOGIN_USER_COUNT);
        }
        return result;
    }


    /**
     * 忘记密码
     * @param username
     * @param oldPwd
     * @return
     */
    @RequestMapping("/forgetPwd")
    public Result forgetPwd(@RequestParam("username")String username,
                                @RequestParam("oldPwd") String oldPwd){
        return playerService.forgetPwd(username, oldPwd);
    }

    /**
     * 重置登录密码
     * @param playerId
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @RequestMapping("/resetLoginPwd")
    public Result resetLoginPwd(@RequestParam("playerId")String playerId,
                                @RequestParam("oldPwd") String oldPwd,
                                @RequestParam("newPwd")  String newPwd){
        return playerService.resetLoginPwd(playerId, oldPwd,newPwd);
    }


    /**
     * 重置交易密码
     * @param playerId
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @RequestMapping("/resetTraderPwd")
    public Result resetTraderPwd(@RequestParam("playerId")String playerId,
                                 @RequestParam("oldPwd") String oldPwd,
                                 @RequestParam("newPwd")  String newPwd){
        return playerService.resetTraderPwd(playerId, oldPwd,newPwd);
    }

    /**
     * 玩家
     * @param playerId
     * @return
     */
    @RequestMapping("/get/{playerId}")
    public Result getPlayer(@PathVariable("playerId")String playerId){
        Player player = playerService.getPlayerById(playerId);
        Result result = null;
        if (player != null){
            result = new Result(CityGlobal.ResultCode.success.getStatus(),JSONObject.toJSONString(player));
        }else {
            result = new Result(CityGlobal.ResultCode.fail.getStatus(),JSONObject.toJSONString(player));
        }
        return result;
    }

    @RequestMapping("/getPlayerByName")
    public Result getPlayerByName(@PathVariable("playerName")String playerName,
                                  @PathVariable("playerNick")String playerNick){
        Player player = playerService.getPlayerByName(playerName,playerNick);
        Result result = null;
        if (player != null){
            result = new Result(CityGlobal.ResultCode.success.getStatus(),JSONObject.toJSONString(player));
        }else {
            result = new Result(CityGlobal.ResultCode.fail.getStatus(),JSONObject.toJSONString(player));
        }
        return result;
    }

    /**
     * 玩家列表
     * @param jsonReq
     * @return
     */
    @RequestMapping("/get")
    public Result getPlayers(@RequestParam("json") String jsonReq){
        Player player = getPlayerFromJsonReq(jsonReq);

        List<Player> players = playerService.getPlayers(player);
        Result result = null;
        if (!CollectionUtils.isEmpty(players)){
            result = new Result(CityGlobal.ResultCode.success.getStatus(),JSONObject.toJSONString(players));
        }else {
            result = new Result(CityGlobal.ResultCode.fail.getStatus(),JSONObject.toJSONString(players));
        }
        return result;
    }



    private Player getPlayerFromJsonReq(String jsonReq){
        UserReq userReq = JSON.parseObject(jsonReq,UserReq.class);
        Player player = new Player();
        player.setPlayerNick(userReq.getNick());
        player.setPlayerInvite(userReq.getInvite());
        player.setPlayerName(userReq.getUsername());
        player.setPlayerId(userReq.getPlayerId());
        return player;
    }

}
