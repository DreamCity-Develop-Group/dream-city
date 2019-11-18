package com.dream.city.player.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.CityGlobal;
import com.dream.city.base.model.Page;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.LoginLog;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerExt;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.model.req.PlayerReq;
import com.dream.city.base.model.req.UserReq;
import com.dream.city.base.model.resp.PlayerResp;
import com.dream.city.base.utils.InvitedCodeUtil;
import com.dream.city.base.utils.RedisKeys;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.player.service.LoginLogServcie;
import com.dream.city.player.service.PlayerExtService;
import com.dream.city.player.service.PlayerHandleService;
import com.dream.city.player.service.PlayerService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.TreeMap;

/**
 * 玩家
 * @author Wvv
 */
@Slf4j
@RestController
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    PlayerService playerService;
    @Autowired
    PlayerHandleService playerHandleService;
    @Autowired
    LoginLogServcie loginLogServcie;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    PlayerExtService playerExtService;


    /**
     * 用户注册
     * @param jsonReq(用户名，用户密码)
     * @return
     */
    @RequestMapping("/reg")
    public Result<JSONObject> reg(@RequestBody String jsonReq){
        log.info("用户注册，{}",jsonReq);
        Result<JSONObject> result = new Result<>();
        if (StringUtils.isBlank(jsonReq) || (StringUtils.isNotBlank(jsonReq) && "{}".equals(jsonReq))){
            result.setSuccess(Boolean.FALSE);
            result.setCode(CityGlobal.ResultCode.fail.getStatus());
            return result;
        }

        Map<String,String> player = JSON.parseObject(jsonReq,Map.class);
        String playerName = player.get("username");
        String playerPass = player.get("userpass");
        //String code = player.get("code");
        String nick = player.get("nick");
        //String invite = player.get("invite");

        String tip = "";
        if(StringUtils.isBlank(playerName) || StringUtils.isBlank(playerPass)){
            tip=  "用户名或密码为空";
            return Result.result(false,tip,ReturnStatus.INVALID.getStatus(),null);
        }
        // 用户是否存在
        PlayerResp playerExistByName = playerService.getPlayerByName(playerName,null);
        if (playerExistByName != null){
            tip = "["+ playerName +"]" + CityGlobal.Constant.REG_USER_EXIT + ",请直接登录！";
            Integer code = ReturnStatus.ACCOUNT_EXISTS.getStatus();
            Result result1 = Result.result(false,tip,code);
            return result1;
        }
        if (StringUtils.isBlank(nick)){
            nick = playerName;
        }
        // 昵称已被使用
        PlayerResp playerExistByNick = playerService.getPlayerByName(null,nick);
        if (playerExistByNick != null){
            tip = "["+ nick +"]" + CityGlobal.Constant.REG_USER_NICK_EXIST;
            return Result.result(false,tip,ReturnStatus.NICK_EXISTS.getStatus(),null);
        }

        /*if(StringUtils.isBlank(code)){
            tip = "短信验证码为空";
        }*/
        // 邀请码：为选填项，可填写可不填；
        /*if(StringUtils.isBlank(invite)){
            tip = "邀请码为空";
        }*/

        //生成用户的邀请码 TODO 邀请码重复待处理
        String inviteCode = InvitedCodeUtil.getCode();
        boolean isExists = (playerService.getPlayerByInvite(inviteCode)==null);
        while (!isExists){
            inviteCode = InvitedCodeUtil.getCode();
            isExists = (playerService.getPlayerByInvite(inviteCode)==null);
        }
        if (StringUtils.isBlank(tip)){
            Player playerSave = new Player();
            playerSave.setPlayerName(playerName);
            playerSave.setPlayerPass(playerPass);
            playerSave.setPlayerInvite(inviteCode);
            playerSave.setPlayerNick(nick);
            //默认设置为不可用，标识推荐码不可用
            playerSave.setIsValid(0);

            boolean ret = playerHandleService.createPlayer(playerSave);

            if (ret){
                tip = "注册成功";
                PlayerResp playerInsert = playerService.getPlayerByName(playerName,nick);
                playerInsert.setPlayerInvite(inviteCode);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("playerId",playerInsert.getPlayerId());
                jsonObject.put("playerInvite",playerInsert.getPlayerInvite());
                result.setSuccess(Boolean.TRUE);
                result.setCode(CityGlobal.ResultCode.success.getStatus());
                result.setData(jsonObject);
                // 登录成功保存redis
                loginToRedis(playerInsert);

                return Result.result(true,tip,ReturnStatus.SUCCESS.getStatus(),playerInsert);
            }
        }else {
            tip="注册失败";
        }

        result.setMsg(tip);
        return result;
    }

    /**
     * 用户密码登录
     * @param jsonReq
     * @return
     */
    @RequestMapping("/login")
    public Result login(@RequestBody String jsonReq){
        log.info("用户密码登录，{}",jsonReq);
        Map map = JSON.parseObject(jsonReq,Map.class);
        Result result = new Result();
        result.setCode(ReturnStatus.ERROR_NOTEXISTS.getStatus());

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
            PlayerResp playerExists= playerService.getPlayerByName(username,null);
            if (playerExists == null){
                result.setMsg(CityGlobal.Constant.USER_NOT_EXIT);
                result.setCode(ReturnStatus.ERROR_NOTEXISTS.getStatus());
                tip.append(CityGlobal.Constant.USER_NOT_EXIT);
            }else {
                //禁止登录用户
                if (playerExists.getIsValid().equals("0")){
                    return Result.result(false,"当前用户禁止登录",ReturnStatus.FAILED.getStatus());
                }
                playerId = playerExists.getPlayerId();
                // 用户名
                if (!playerExists.getPlayerName().equalsIgnoreCase(username)){
                    tip.append(CityGlobal.Constant.USER_NOT_EXIT);
                    result.setCode(ReturnStatus.ERROR_NOTEXISTS.getStatus());
                }
                // 密码
                String redisKey = RedisKeys.LOGIN_USER_TOKEN + username;
                if (!redisUtils.hasKey(redisKey) && !playerExists.getPlayerPass().equals(userpass)) {
                    tip.append(CityGlobal.Constant.USER_PWD_ERROR);
                    result.setCode(ReturnStatus.ERROR_PASS.getStatus());
                }
                if (StringUtils.isBlank(tip.toString())) {
                    tip.append(CityGlobal.Constant.LOGIN_SUCCESS);
                    result.setCode(CityGlobal.ResultCode.success.getStatus());
                    result.setSuccess(Boolean.TRUE);
                    result.setData(playerId);
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
    private void loginToRedis(PlayerResp player){
        log.info("登录成功保存redis，{}",player);
        String key = RedisKeys.CURRENT_USER + player.getPlayerName();
        if (redisUtils.hasKey(key)){
            redisUtils.del(key);
        }

        redisUtils.set(key,
                JSON.toJSONString(player));

        redisUtils.incr(RedisKeys.CURRENT_LOGIN_USER_COUNT);
    }

    /**
     * 验证码登录
     * @param jsonReq
     * @return
     */
    @RequestMapping("/codelogin")
    public Result codeLogin(@RequestBody String jsonReq){
        log.info("验证码登录，{}",jsonReq);
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
            PlayerResp playerExists = playerService.getPlayerByName(username,null);
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
                    result.setData(playerId);
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
        log.info("用户退出，playerId:{}",playerId);
        Result result = Result.result(Boolean.TRUE,"退出成功",CityGlobal.ResultCode.success.getStatus());
        // 用户是否存在
        Player player = new Player();
        player.setPlayerId(playerId);
        PlayerResp playerExit = playerService.getPlayer(player);
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
        //Token清除
        if(redisUtils.hasKey(RedisKeys.LOGIN_USER_TOKEN+playerExit.getPlayerName())){
            redisUtils.del(RedisKeys.LOGIN_USER_TOKEN+playerExit.getPlayerName());
        }
        //缓存删除
        redisUtils.rmOnlinePlayer(playerExit.getPlayerName());
        if(redisUtils.hasKey(RedisKeys.MAIN_HASH_DATA+playerExit.getPlayerName())){
            redisUtils.del(RedisKeys.MAIN_HASH_DATA+playerExit.getPlayerName());
        }
        //下线
        if (redisUtils.hasKey(RedisKeys.PLAYER_ONLINE_STATE_KEY+playerExit.getPlayerName())){
            redisUtils.del(RedisKeys.PLAYER_ONLINE_STATE_KEY+playerExit.getPlayerName());
        }
        return result;
    }

    @RequestMapping("/quitAccount")
    public Result quitAccount(@RequestParam("account")String account){
        log.info("用户退出，account:{}",account);
        Player playerByAccount = playerService.getPlayerByAccount(account);
        Result result = Result.result(Boolean.TRUE,"退出成功",CityGlobal.ResultCode.success.getStatus());
        // 用户是否存在
        Player player = new Player();
        String playerId = playerByAccount.getPlayerId();
        player.setPlayerId(playerId);
        PlayerResp playerExit = playerService.getPlayer(player);
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
        if (redisUtils.hasKey(RedisKeys.CURRENT_USER + playerExit.getPlayerName())) {
            redisUtils.del(RedisKeys.CURRENT_USER + playerExit.getPlayerName());
            redisUtils.decr(RedisKeys.CURRENT_LOGIN_USER_COUNT);
        }
        //Token清除
        if(redisUtils.hasKey(RedisKeys.LOGIN_USER_TOKEN+playerExit.getPlayerName())){
            redisUtils.del(RedisKeys.LOGIN_USER_TOKEN+playerExit.getPlayerName());
        }
        //缓存删除
        redisUtils.rmOnlinePlayer(playerExit.getPlayerName());
        if(redisUtils.hasKey(RedisKeys.MAIN_HASH_DATA+playerExit.getPlayerName())){
            redisUtils.del(RedisKeys.MAIN_HASH_DATA+playerExit.getPlayerName());
        }
        //下线
        if (redisUtils.hasKey(RedisKeys.PLAYER_ONLINE_STATE_KEY+playerExit.getPlayerName())){
            redisUtils.del(RedisKeys.PLAYER_ONLINE_STATE_KEY+playerExit.getPlayerName());
        }
        return result;
    }


    /**
     * 忘记密码
     * @param username
     * @param newpw
     * @return
     */
    @RequestMapping("/forgetPwd")
    public Result forgetPwd(
            @RequestParam("username")String username,
            @RequestParam("newpw") String newpw){
        log.info("忘记密码，userName:{},newpw:{}",username,newpw);
        return playerService.forgetPwd(username, newpw);
    }

    /**
     * 重置登录密码
     * @param username
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @RequestMapping("/resetLoginPwd")
    public Result resetLoginPwd(@RequestParam("username")String username,
                                @RequestParam("oldPwd") String oldPwd,
                                @RequestParam("newPwd")  String newPwd){
        log.info("重置登录密码，username:{},oldPwd:{},newPwd:{}",username,oldPwd,newPwd);
        return playerService.resetLoginPwd(username, oldPwd,newPwd);
    }


    /**
     * 重置交易密码
     * @param username
     * @param oldpwshop
     * @param newpwshop
     * @return
     */
    @RequestMapping("/resetTraderPwd")
    public Result resetTraderPwd(@RequestParam("username")String username,
                                 @RequestParam("oldpwshop") String oldpwshop,
                                 @RequestParam("newpwshop")  String newpwshop){
        log.info("重置交易密码，username:{},oldpwshop:{},newpwshop:{}",username,oldpwshop,newpwshop);
        return playerService.resetTraderPwd(username, oldpwshop,newpwshop);
    }

    /**
     * 重置交易密码
     * @param playerId
     * @param tradePass
     * @return
     */
    @RequestMapping("/setTraderPwd")
    public Result resetTraderPwd(@RequestParam("playerId")String playerId,
                                 @RequestParam("tradePass") String tradePass){
        log.info("设置交易密码，playerId:{},newpwshop:{}",playerId,tradePass);
        Player player = playerService.getPlayerByPlayerId(playerId);
        if (player!=null && StringUtils.isNotBlank(player.getPlayerTradePass())){
            return Result.result(true,"交易密码已经设置",ReturnStatus.SUCCESS.getStatus());
        }
        boolean ret =  playerService.setTraderPwd(playerId, tradePass);

        return Result.result(ret,"设置交易密码结果",
                    ret?ReturnStatus.SUCCESS.getStatus():ReturnStatus.SET_FAILED.getStatus()
                );
    }

    /**
     * 玩家
     * @param playerId
     * @return
     */
    @RequestMapping("/get/{playerId}")
    public Result<JSONObject> getPlayer(@PathVariable("playerId")String playerId){
        log.info("获取玩家，playerId:{}",playerId);
        PlayerResp player = playerService.getPlayerById(playerId);
        Result result = null;
        if (player != null){
            result = new Result<>(CityGlobal.ResultCode.success.getStatus(),JSONObject.toJSONString(player));
        }else {
            result = new Result<>(CityGlobal.ResultCode.fail.getStatus(),JSONObject.toJSONString(player));
        }
        return result;
    }


    /**
     * 广场玩家列表
     * @param pageReq
     * @return
     */
    @RequestMapping("/getPlayers")
    public Result<PageInfo> getPlayers(@RequestBody Page pageReq){
        log.info("获取广场玩家列表，{}",pageReq);
        Result result = null;
        PageInfo<PlayerResp> page = null;
        try {
            page = playerService.getPlayers(pageReq);
            result = new Result<>(CityGlobal.ResultCode.success.getStatus(),page);
        }catch (Exception e){
            result = new Result<>(CityGlobal.ResultCode.fail.getStatus(),null);
        }

        return result;
    }
    @RequestMapping("/getPlayersCount")
    public Integer getPlayersCount(@RequestBody PlayerReq record){
        log.info("获取广场玩家列表，{}",record);
        Integer count = null;
        try {
            count = playerService.getPlayersCount(record);
        }catch (Exception e){
        }

        return count;
    }


    @RequestMapping(value = "/getPlayerByName",method = RequestMethod.POST,produces="application/json; utf-8")
    public Result<String> getPlayerByName(@RequestBody String jsonReq){
        log.info("根据用户名或昵称获取玩家，{}",jsonReq);
        JSONObject jsonObject = JSON.parseObject(jsonReq);
        String playerName = jsonObject.getString("playerName");
        String playerNick = jsonObject.getString("playerNick");
        PlayerResp player = playerService.getPlayerByName(playerName,playerNick);
        Result<String> result = null;
        if (player != null){
            result = new Result<>(CityGlobal.ResultCode.success.getStatus(),JSONObject.toJSONString(player));
        }else {
            result = new Result<>(CityGlobal.ResultCode.fail.getStatus(),JSONObject.toJSONString(player));
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

    @RequestMapping("/getPlayerByInvite")
    public Result getPlayerByInvite(@RequestParam("invite")String invite){
        Player player = playerService.getPlayerByInvite(invite);
        //Result<Player>result = new Result<>(true,);
        Result result = Result.result(true,"获取玩家",ReturnStatus.SUCCESS.getStatus(),player);
        return result;
    }

    @RequestMapping("/getPlayerByPlayerId")
    public Player getPlayerByPlayerId(@RequestParam("playerId")String playerId){
        Player player = playerService.getPlayerByPlayerId(playerId);

        return player;
    }

    @RequestMapping("/updatePlayer")
    public Player updatePlayer(@RequestBody Player player){
        Player player1 = playerService.update(player);

        return player1;
    }

    @RequestMapping("/getPlayerByAccount")
    public Result getPlayerByAccount(@RequestParam("account")String account){
        Player player = playerService.getPlayerByAccount(account);
        //Result<Player>result = new Result<>(true,"获取玩家",200,player);
        Result result = Result.result(true,"获取玩家",ReturnStatus.SUCCESS.getStatus(),player);
        return result;
    }


    /**
     * 修改玩家头像
     * @param record
     * @return
     */
    @RequestMapping("/updatePlayerHeadImg")
    public Result<Boolean> updatePlayerHeadImg(@RequestBody PlayerExt record){
        Integer integer = playerExtService.updatePlayerExtByPlayerId(record);
        if (integer != null && integer > 0){
            return Result.result(true,"success",ReturnStatus.SUCCESS.getStatus(),true);
        }
        return new Result<Boolean>(false,"fail",ReturnStatus.FAILED.getStatus(),false);
    }

    @RequestMapping("/checkInvite")
    public Result checkPlayerInvite(@RequestParam("invite")String invite){
        Player player = playerService.getPlayerByInvite(invite);
        if (player != null){
            return Result.result(true);
        }
        return Result.result(false);
    }

    @RequestMapping("/setTradePassword")
    public Result setTradePassword(@RequestBody Player player){

        if (player != null){
            Player p = playerService.update(player);
            if (p!=null) {
                return Result.result(true);
            }
        }
        return Result.result(false);
    }

}
