package com.dream.city.player.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.CityGlobal;
import com.dream.city.base.model.Page;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.req.PageReq;
import com.dream.city.base.model.req.UserReq;
import com.dream.city.base.utils.InvitedCodeUtil;
import com.dream.city.base.utils.RedisKeys;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.player.domain.entity.LoginLog;
import com.dream.city.player.domain.entity.Player;
import com.dream.city.player.service.LoginLogServcie;
import com.dream.city.player.service.PlayerService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
    public Result reg(@RequestBody String jsonReq){
        logger.info("用户注册，{}",jsonReq);
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
        Player playerExistByName = playerService.getPlayerByName(playerName,null);
        if (playerExistByName != null){
            tip = "["+ playerName +"]" + CityGlobal.Constant.REG_USER_EXIT + ",请直接登录！";
        }

        // 昵称已被使用
        Player playerExistByNick = playerService.getPlayerByName(null,nick);
        if (playerExistByNick != null){
            tip = "["+ nick +"]" + CityGlobal.Constant.REG_USER_NICK_EXIST;
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
        //生成用户的邀请码
        String inviteCode = InvitedCodeUtil.getCode();
        if (StringUtils.isBlank(tip)){
            Player playerSave = new Player();
            playerSave.setPlayerName(playerName);
            playerSave.setPlayerPass(playerPass);
            playerSave.setPlayerInvite(inviteCode);
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
    public Result pwLogoin(@RequestBody String jsonReq){
        logger.info("用户密码登录，{}",jsonReq);
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
        logger.info("登录成功保存redis，{}",player);
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
    public Result codeLogoin(@RequestBody String jsonReq){
        logger.info("验证码登录，{}",jsonReq);
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
        logger.info("用户退出，playerId:{}",playerId);
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
        logger.info("忘记密码，userName:{},oldPwd:{}",username,oldPwd);
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
        logger.info("重置登录密码，playerId:{},oldPwd:{},newPwd:{}",playerId,oldPwd,newPwd);
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
        logger.info("重置交易密码，playerId:{},oldPwd:{},newPwd:{}",playerId,oldPwd,newPwd);
        return playerService.resetTraderPwd(playerId, oldPwd,newPwd);
    }

    /**
     * 玩家
     * @param playerId
     * @return
     */
    @RequestMapping("/get/{playerId}")
    public Result getPlayer(@PathVariable("playerId")String playerId){
        logger.info("获取玩家，playerId:{}",playerId);
        Player player = playerService.getPlayerById(playerId);
        Result result = null;
        if (player != null){
            result = new Result(CityGlobal.ResultCode.success.getStatus(),JSONObject.toJSONString(player));
        }else {
            result = new Result(CityGlobal.ResultCode.fail.getStatus(),JSONObject.toJSONString(player));
        }
        return result;
    }


    /**
     * 广场玩家列表
     * @param pageReq
     * @return
     */
    @RequestMapping("/getPlayers")
    public Result<String> getPlayers(@RequestBody PageReq pageReq){
        logger.info("获取广场玩家列表，{}",pageReq);
        Result result = null;
        Page page = null;

        try {
            page = playerService.getPlayers(pageReq);
            result = new Result(CityGlobal.ResultCode.success.getStatus(),JSON.toJSONString(page));
        }catch (Exception e){
            result = new Result(CityGlobal.ResultCode.fail.getStatus(),null);
        }

        return result;
    }


    @RequestMapping(value = "/getPlayerByName",method = RequestMethod.POST,produces="application/json; utf-8")
    public Result getPlayerByName(@RequestBody String jsonReq){
        logger.info("根据用户名或昵称获取玩家，{}",jsonReq);
        JSONObject jsonObject = JSON.parseObject(jsonReq);
        String playerName = jsonObject.getString("playerName");
        String playerNick = jsonObject.getString("playerNick");
        Player player = playerService.getPlayerByName(playerName,playerNick);
        Result result = null;
        if (player != null){
            result = new Result(CityGlobal.ResultCode.success.getStatus(),JSONObject.toJSONString(player));
        }else {
            result = new Result(CityGlobal.ResultCode.fail.getStatus(),JSONObject.toJSONString(player));
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
