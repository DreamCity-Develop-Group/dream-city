package com.dream.city.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.*;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.PlayerExt;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.model.req.UserReq;
import com.dream.city.base.model.resp.PlayerResp;
import com.dream.city.base.service.DictionaryService;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.base.utils.RedisKeys;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.*;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
    private ConsumerPlayerService consumerPlayerService;
    @Autowired
    private ConsumerMessageService messageService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    ConsumerAuthService authService;
    @Autowired
    private ConsumerTreeService treeService;
    //用户平台账户
    @Autowired
    ConsumerPlayerAccountService playerAccountService;
    //区块账户
    @Autowired
    ConsumerPlayerBlockChainService playerBlockChainService;
    //区块账户
    @Autowired
    ConsumerFriendsService friendsService;
    @Autowired
    ConsumerCommonsService commonsService;
    @Autowired
    DictionaryService dictionaryService;

    /**
     * 修改玩家头像
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "修改玩家头像", httpMethod = "POST", notes = "修改玩家头像，参数：username,imgUrl", response = Message.class)
    @RequestMapping("/updatePlayerHeadImg")
    public Message updatePlayerHeadImg(@RequestBody Message msg) {
        log.info("修改玩家头像", JSONObject.toJSONString(msg));
        UserReq jsonReq = DataUtils.getUserReq(msg);
        PlayerResp player = commonsService.getPlayerByUserName(msg);
        PlayerExt record = new PlayerExt();
        record.setPlayerId(player.getPlayerId());
        record.setImgurl(jsonReq.getImgUrl());
        Result<Boolean> result = consumerPlayerService.updatePlayerHeadImg(record);

        MessageData messageData = new MessageData(msg.getData().getType(), msg.getData().getModel(), result.getCode());
        messageData.setData(result.getData());
        Message message = new Message(msg.getSource(), msg.getTarget(), messageData);
        message.setDesc(result.getMsg());
        return message;
    }


    @RequestMapping("/searchFriend")
    @ApiOperation(value = "换一批广场玩家列表", httpMethod = "POST", notes = "换一批广场玩家列表", response = Message.class)
    public Message searchfriend(@RequestBody Message msg) {
        log.info("广场玩家列表 换一批", JSONObject.toJSONString(msg));

        UserReq jsonReq = DataUtils.getUserReq(msg);
        Player playerReq = new Player();
        playerReq.setPlayerId(jsonReq.getPlayerId());
        playerReq.setPlayerName(jsonReq.getUsername());
        Page pageReq = new Page();
        pageReq.setCondition(playerReq);

        int pageNo = 1;
        String redisKey = RedisKeys.SQUARE_PLAYER_LIST_ANOTHER_BATCH + jsonReq.getPlayerId();
        if (redisUtils.hasKey(redisKey)) {
            pageNo = Integer.parseInt(redisUtils.getStr(redisKey));
            pageNo = pageNo + 1;
        } else {
            redisUtils.setStr(redisKey, String.valueOf(pageNo));
        }
        pageReq.setPageNum(pageNo);

        Result<PageInfo> players = consumerPlayerService.getPlayers(pageReq);
        msg.setDesc(players.getMsg());
        msg.setCode(ReturnStatus.SUCCESS.getStatus());
        msg.getData().setCode(ReturnStatus.SUCCESS.getStatus());
        msg.getData().setData(players.getData());
        return msg;
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
        log.info("获取认证码", JSONObject.toJSONString(msg));
        Map<String, Object> map = new HashMap<>();
        Result code = messageService.getCode(msg);
        MessageData messageData = new MessageData(msg.getData().getType(), msg.getData().getModel());
        if (code.getSuccess()) {
            map.put("code", code.getData());
            map.put("desc", "获取认证码成功");

            messageData.setData(map);
            Message message = new Message(msg.getSource(), msg.getTarget(), messageData);
            return message;
        } else {
            messageData.setCode(code.getCode());
            msg.setDesc(code.getMsg());
            msg.setData(messageData);
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
        log.info("获取用户", JSONObject.toJSONString(msg));
        Map<String, Object> map = (Map<String, Object>) msg.getData().getData();
        String id = map.get("id").toString();
        Result player = consumerPlayerService.getPlayer(id);

        return Message.generateMessage(msg, player);
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
        log.info("广场玩家列表", JSONObject.toJSONString(msg));
        UserReq jsonReq = DataUtils.getUserReq(msg);
        //当前用户id
        String playerId = jsonReq.getPlayerId();
        //查询用户
        String username = jsonReq.getUsername();
        String nick = null;

        PlayerResp playerByStrUserName = commonsService.getPlayerByStrUserName(username);
        PlayerResp playerByStrNick = commonsService.getPlayerByStrNick(username);
        if (playerByStrUserName != null) {
            nick = playerByStrUserName.getPlayerNick();
        }
        if (playerByStrNick != null) {
            username = playerByStrNick.getPlayerName();
            nick = playerByStrNick.getPlayerNick();
        }

        Player playerReq = new Player();
        playerReq.setPlayerId(playerId);
        playerReq.setPlayerName(username);
        playerReq.setPlayerNick(nick);
        Page pageReq = new Page();
        pageReq.setCondition(playerReq);
        Result<PageInfo> players = consumerPlayerService.getPlayers(pageReq);

        msg.setDesc(players.getMsg());
        msg.getData().setData(players.getData());
        msg.setCode(ReturnStatus.SUCCESS.getStatus());
        msg.getData().setCode(ReturnStatus.SUCCESS.getStatus());
        return msg;
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
        log.info("忘记密码", JSONObject.toJSONString(msg));
        UserReq jsonReq = DataUtils.getUserReq(msg);
        Result result = consumerPlayerService.forgetPwd(jsonReq.getUsername(), jsonReq.getNewpw());

        Map<String, String> t = new HashMap<>();
        t.put("desc", result.getMsg());
        MessageData data = new MessageData(msg.getData().getType(), msg.getData().getModel());
        data.setData(t);
        Message message = new Message(msg.getSource(), msg.getTarget(), data);
        return message;
    }

    /**
     * 修改密码
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "修改密码", httpMethod = "POST", notes = "修改密码", response = Message.class)
    @RequestMapping("/expw")
    public Message resetLoginPwd(@RequestBody Message msg) {
        log.info("修改密码", JSONObject.toJSONString(msg));
        UserReq jsonReq = DataUtils.getUserReq(msg);
        Result result = consumerPlayerService.resetLoginPwd(jsonReq.getUsername(), jsonReq.getOldpw(), jsonReq.getNewpw());

        log.info("##################### 修改密码 : {}", msg);
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("desc", result.getMsg());

        MessageData data = new MessageData(msg.getData().getType(), msg.getData().getModel());
        //添加判断状态码
        data.setCode(result.getCode());
        if (result.getSuccess()){
            data.setCode(ReturnStatus.SUCCESS.getStatus());
        }

        data.setData(dataMap);
        Message message = new Message(msg.getSource(), msg.getTarget(), data);
        return message;
    }

    /**
     * 设置、修改交易密码
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "设置、修改交易密码", httpMethod = "POST", notes = "修改交易密码，没有交易密码的设置交易密码，有交易密码的修改交易密码", response = Message.class)
    @RequestMapping("/expwshop")
    public Message expwshop(@RequestBody Message msg) {
        log.info("修改交易密码", JSONObject.toJSONString(msg));
        UserReq jsonReq = DataUtils.getUserReq(msg);
        PlayerResp player = commonsService.getPlayerByUserName(msg);

        Result result = consumerPlayerService.resetTraderPwd(jsonReq.getUsername(), jsonReq.getOldpwshop(), jsonReq.getNewpwshop());
        log.info("##################### 修改交易密码 : {}", msg);
        Map<String, String> t = new HashMap<>();
        t.put("desc", result.getMsg());

        if (result.getSuccess()) {
            if (StringUtils.isNotBlank(player.getPlayerTradePass())) {
                //修改交易密码，扣除1MT
                //玩家账户
                PlayerAccount playerAccount = playerAccountService.getPlayerAccount(player.getPlayerId());
                //扣除玩家1MT TODO

                //平台账户
                String[] idsArr = dictionaryService.getValByKey("platform.account.accIds").split(",");
                PlayerAccount playerAccount1 = playerAccountService.getPlayerAccount(idsArr[0]);
                //平台账户增加1MT TODO

            }
        }

        MessageData data = new MessageData(msg.getData().getType(), msg.getData().getModel());
        data.setData(t);
        Message message = new Message(msg.getSource(), msg.getTarget(), data);
        return message;
    }


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
    @ApiOperation(value = "用户注册", httpMethod = "POST", notes = "用户注册", response = Message.class)
    @RequestMapping("/reg")
    public Message reg(@RequestBody Message message) {
        long start = System.currentTimeMillis();
        long end;
        log.info("用户注册", JSONObject.toJSONString(message));
        UserReq userReq = DataUtils.getUserReq(message);
        String jsonReq = JSON.toJSONString(userReq);
        Message msg = new Message(
                message.getTarget(),
                "server",
                new MessageData(message.getData().getType(), message.getData().getModel())
        );


        if (null == message.getData() || null == message.getData().getData()) {
            msg.setDesc("参数错误或不能识别");
            msg.setCreatetime(String.valueOf(System.currentTimeMillis()));
            msg.getData().setCode(ReturnStatus.ERROR.getStatus());
            return msg;
        }

        String jsonData = JsonUtil.parseObjToJson(message.getData().getData());
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String account = jsonObject.getString("username");
        String code = jsonObject.getString("code");
        String invite = jsonObject.getString("invite");
        //参数判断
        if (StringUtils.isBlank(account) || StringUtils.isBlank(code)) {
            msg.setDesc("参数值不能为空");
            msg.setCreatetime(String.valueOf(System.currentTimeMillis()));
            msg.getData().setCode(ReturnStatus.ACCOUNT_PASS_REQUIRED.getStatus());
            return msg;
        }
        /*todo************************************************************/
        // TODO [[验证码验证]]
        /*todo************************************************************/
        Result ret = messageService.checkCode(code, account);

        /*todo************************************************************/
        // TODO [[邀请码验证]]
        /*todo************************************************************/
        if (StringUtils.isNotBlank(invite)) {
            Result retInvite = consumerPlayerService.checkPlayerInvite(invite);
            if (!retInvite.getSuccess()) {
                msg.setDesc("邀请码错误");
                msg.setCreatetime(String.valueOf(System.currentTimeMillis()));
                msg.getData().setCode(ReturnStatus.ERROR_INVITE.getStatus());
                return msg;
            } else {
                String player = JsonUtil.parseObjToJson(consumerPlayerService.getPlayerByInvite(invite).getData());
                JSONObject playerJson = JsonUtil.parseJsonToObj(player, JSONObject.class);
                if (!playerJson.getString("isValid").equals("1")) {
                    msg.setDesc("邀请码暂不可用");
                    msg.setCreatetime(String.valueOf(System.currentTimeMillis()));
                    msg.getData().setCode(ReturnStatus.ERROR_RECEIVED.getStatus());
                    return msg;
                }
            }
        }

        String desc = CityGlobal.Constant.REG_FAIL;
        Result<JSONObject> reg = null;
        //验证成功
        if (ret.getSuccess()) {
            /*todo************************************************************/
            // TODO [[插入玩家信息]]
            /*todo************************************************************/
            reg = consumerPlayerService.reg(jsonReq);
            log.info("##################### 玩家注册: {}", msg);

            //玩家注册成功
            if (reg.getSuccess()) {
                /*todo************************************************************/
                // TODO [[取出message 中的账户和邀请码]]
                /*todo************************************************************/
                String json = JsonUtil.parseObjToJson(message);
                JSONObject jsonMsg = JSON.parseObject(json);
                JSONObject dataMsg = jsonMsg.getJSONObject("data").getJSONObject("data");

                String username = dataMsg.getString("username");
                //todo 1、取到注册成功的用户信息
                JSONObject jsonObject1 = JSON.parseObject(JsonUtil.parseObjToJson(reg.getData()));
                String playerId = jsonObject1.getString("playerId");
                String playerInvite = jsonObject1.getString("playerInvite");

                /*todo************************************************************/
                // TODO [[todo 2、创建用户USDT钱包账户]]
                /*todo************************************************************/
                Result accRet = playerBlockChainService.createBlockChainAccount(username);
                if (accRet.getSuccess()){
                    String address = accRet.getData().toString();
                    log.info("玩家账户地址：[" + address + "]创建成功");
                    if (!StringUtils.isBlank(address)) {
                        Result create = playerAccountService.createAccount(playerId, address);
                        if (create.getSuccess()) {
                            log.info("玩家账户创建成功");
                        }else{
                            log.info("玩家账户创建失败，重试一次");
                            create = playerAccountService.createAccount(playerId, address);
                        }
                    }
                }else{
                    log.info("玩家账户创建不成功，重试一次");
                    accRet = playerBlockChainService.createBlockChainAccount(username);
                    if (accRet.getSuccess()){
                        String address = accRet.getData().toString();
                        log.info("玩家账户地址：[" + address + "]创建成功");
                        if (!StringUtils.isBlank(address)) {
                            Result create = playerAccountService.createAccount(playerId, address);
                            if (create.getSuccess()) {
                                log.info("玩家账户创建成功");
                            }
                        }
                    }else{
                        log.info("玩家账户创建不成功");
                    }
                }

                /*todo************************************************************/
                // TODO [[todo 3、邀请码不为空，设置商会关系,邀请码为空，不设置关系]]
                /*todo************************************************************/
                if (!StringUtils.isBlank(invite)) {
                    Result resultParent = consumerPlayerService.getPlayerByInvite(invite);
                    Player parent = JsonUtil.parseJsonToObj((JSON.toJSONString(resultParent.getData())), Player.class);

                    String parentId = parent.getPlayerId();

                    //商会关系
                    Result result = treeService.addTree(parentId, playerId, playerInvite);
                    if (result.getSuccess()) {
                        log.info("商会关系创建完成");
                    }
                    //创建好友关系 待同意
                    Result<Boolean> addFriend = friendsService.addFriend(playerId, parentId, invite);

                    if (addFriend.getSuccess()) {
                        log.info("添加默认好友关系成功");
                    }
                } else {
                    log.info("没有写邀请码");
                }

                String regSuccess = CityGlobal.Constant.REG_SUCCESS;

                /*todo************************************************************/
                // TODO [[登录或注册成功后保存token，并且不用再次登录可进入程序]]
                /*todo************************************************************/
                String token = saveToken(userReq.getUsername());
                Map map = new HashMap();
                map.put("token",token);
                msg.setDesc(regSuccess);
                msg.getData().setCode(ReturnStatus.SUCCESS.getStatus());
                msg.getData().setData(map);
                end = System.currentTimeMillis();
                return msg;
            } else {
                msg.getData().setCode(reg.getCode());
                msg.setDesc(reg.getMsg());
                end = System.currentTimeMillis();
                return msg;
            }
        }else{
            msg.getData().setCode(ret.getCode());
            msg.setDesc(ret.getMsg());
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
        log.info("密码登录", JSONObject.toJSONString(msg));
        UserReq userReq = DataUtils.getUserReq(msg);
        String jsonReq = JSON.toJSONString(userReq);

        Result result = consumerPlayerService.login(jsonReq);
        log.info("##################### 用户登录: {}", result);

        Map<String, String> data = new HashMap<>();
        String desc = CityGlobal.Constant.LOGIN_FAIL;
        if (result.getSuccess()) {
            String playerId = result.getData().toString();
            desc = CityGlobal.Constant.LOGIN_SUCCESS;
            Result playerRet = consumerPlayerService.getPlayer(playerId);
            Player player = JsonUtil.parseJsonToObj(playerRet.getData().toString(), Player.class);
            String token = saveToken(userReq.getUsername());
            data.put("token", token);
            data.put("playerId", playerId);
            data.put("id", player.getId().toString());
            data.put("playerName", player.getPlayerName());
        }
        MessageData msgData = new MessageData(
                msg.getData().getType(),
                msg.getData().getModel(),
                data,
                result.getCode()
        );
        Message message = new Message(msg.getSource(), msg.getTarget(), msgData, result.getMsg());
        return message;
    }

    /**
     * 登录或注册成功后保存token
     *
     * @param username
     */
    private String saveToken(String username) {
        String token = authService.getAuth(username);

        String redisKey = RedisKeys.LOGIN_USER_TOKEN + username;
        if (redisUtils.hasKey(redisKey)) {
            redisUtils.del(redisKey);
        }
        redisUtils.setStr(redisKey, token);

        return token;
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
        log.info("验证码登录", JSONObject.toJSONString(msg));
        Message message = new Message();
        message.setSource(msg.getSource());
        message.setTarget(msg.getTarget());
        MessageData data = new MessageData("codeLogin", "consumer");
        Map<String, String> dataInner = new HashMap<>();

        UserReq userReq = DataUtils.getUserReq(msg);
        // 校验认证码
        Result checkRet = messageService.checkCode(userReq.getCode(), userReq.getUsername());
        //String descMsg = checkCode(userReq.getCode(), msg.getSource());
        String descMsg = checkRet.getMsg();
        String descT = CityGlobal.Constant.LOGIN_FAIL;
        Map<String, String> ret = new HashMap<>();
        if (checkRet.getSuccess()) {
            Result idlog = consumerPlayerService.codeLogin(JSON.toJSONString(userReq));
            log.info("##################### 验证码登录: {}", idlog);
            descMsg = idlog.getMsg();

            if (idlog.getSuccess()) {
                log.info("验证码登录成功");
                descT = CityGlobal.Constant.LOGIN_SUCCESS;
                String playerId = idlog.getData().toString();

                Result playerRet = consumerPlayerService.getPlayer(playerId);
                Player player = JsonUtil.parseJsonToObj(playerRet.getData().toString(), Player.class);
                String token = saveToken(userReq.getUsername());
                ret.put("token", token);
                ret.put("playerId", playerId);
                ret.put("id", player.getId().toString());
                ret.put("playerName", player.getPlayerName());
            } else {
                log.info("验证码登录失败");
            }
        }

        dataInner.put("desc", descT);
        data.setData(dataInner);
        message.setData(data);
        message.setDesc(descMsg);
        return message;
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
        log.info("登出", JSONObject.toJSONString(msg));
        UserReq jsonReq = DataUtils.getUserReq(msg);
        String account = jsonReq.getUsername();
        String playerId = jsonReq.getPlayerId();
        Result result = Result.result(false);
        if (StringUtils.isNotBlank(playerId)) {
            result = consumerPlayerService.quit(playerId);

        } else {
            result = consumerPlayerService.quitAccount(account);
        }
        log.info("##################### 用户登出 :{}", msg);

        String redisKey = RedisKeys.LOGIN_USER_TOKEN + jsonReq.getUsername();
        if (redisUtils.hasKey(redisKey)) {
            redisUtils.del(redisKey);
        }

        redisKey = RedisKeys.SQUARE_PLAYER_LIST_ANOTHER_BATCH + jsonReq.getUsername();
        redisUtils.del(redisKey);

        //删除在线用户
        boolean ret = redisUtils.rmOnlinePlayer(account);
        if (!ret) {
            ret = redisUtils.rmOnlinePlayer(account);
            if (!ret) {
                ret = redisUtils.rmOnlinePlayer(account);
            }
        }

        Map<String, String> t = new HashMap<>();
        t.put("desc", result.getMsg());
        MessageData data = new MessageData(msg.getData().getType(), msg.getData().getModel());
        data.setData(t);
        Message message = new Message(msg.getSource(), msg.getTarget(), data);
        return message;
    }


}