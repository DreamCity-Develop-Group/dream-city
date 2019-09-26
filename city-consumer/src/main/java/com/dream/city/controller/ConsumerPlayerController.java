package com.dream.city.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.CityGlobal;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerExt;
import com.dream.city.base.model.req.PageReq;
import com.dream.city.base.model.req.UserReq;
import com.dream.city.base.model.resp.PlayerResp;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.base.utils.RedisKeys;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    private CityMessageService messageService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    AuthService authService;
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


    /**
     * 修改玩家头像
     * @param msg
     * @return
     */
    @ApiOperation(value = "修改玩家头像", notes = "修改玩家头像，参数：username,imgUrl", response = Message.class)
    @RequestMapping("/updatePlayerHeadImg")
    public Message updatePlayerHeadImg(@RequestBody Message msg){
        log.info("修改玩家头像", JSONObject.toJSONString(msg));
        UserReq jsonReq = DataUtils.getUserReq(msg);
        PlayerResp player = commonsService.getPlayerByUserName(msg);
        PlayerExt record = new PlayerExt();
        record.setPlayerId(player.getPlayerId());
        record.setImgurl(jsonReq.getImgUrl());
        Result<Boolean> result = consumerPlayerService.updatePlayerHeadImg(record);

        MessageData messageData = new MessageData(msg.getData().getType(), msg.getData().getModel());
        messageData.setData(result.getData());
        Message message = new Message(msg.getSource(), msg.getTarget(), messageData);
        message.setDesc(result.getMsg());
        return message;
    }


    @RequestMapping("/searchfriend")
    @ApiOperation(value = "换一批广场玩家列表", notes = "换一批广场玩家列表", response = Message.class)
    public Message searchfriend(@RequestBody Message msg) {
        log.info("广场玩家列表 换一批", JSONObject.toJSONString(msg));

        UserReq jsonReq = DataUtils.getUserReq(msg);
        PageReq<String> pageReq = new PageReq<>();

        int pageNo = 1;
        String redisKey = RedisKeys.SQUARE_PLAYER_LIST_ANOTHER_BATCH + jsonReq.getUsername();
        if (redisUtils.hasKey(redisKey)) {
            pageNo = Integer.parseInt(redisUtils.getStr(redisKey));
            pageNo = pageNo + 1;
        } else {
            redisUtils.setStr(redisKey, String.valueOf(pageNo));
        }
        pageReq.setPageNo(pageNo);

        Result<String> players = consumerPlayerService.getPlayers(pageReq);
        Map<String, Object> t = new HashMap<>();
        t.put("userList", players.getData());
        MessageData messageData = new MessageData(msg.getData().getType(), msg.getData().getModel());
        messageData.setData(t);
        Message message = new Message(msg.getSource(), msg.getTarget(), messageData);
        message.setDesc(players.getMsg());
        return message;
    }


    /**
     * 获取认证码
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "获取认证码", notes = "获取认证码", response = Message.class)
    @RequestMapping("/getCode")
    public Message getCode(@RequestBody Message msg) {
        log.info("获取认证码", JSONObject.toJSONString(msg));
        Map<String, Object> map = new HashMap<>();
        Result code = messageService.getCode(msg);
        if (code.getSuccess()) {
            map.put("code", code.getData());
            MessageData messageData = new MessageData(msg.getData().getType(), msg.getData().getModel());
            messageData.setData(map);
            Message message = new Message(msg.getSource(), msg.getTarget(), messageData);
            return message;
        }else {
            msg.setDesc(code.getMsg());
            return msg;
        }
    }


    /**
     * 获取当前用户
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "获取当前用户", notes = "获取当前用户", response = Message.class)
    @RequestMapping("/get/user")
    public Message getUser(@RequestBody Message msg) {
        log.info("获取用户", JSONObject.toJSONString(msg));
        Map<String, Object> map = (Map<String, Object>) msg.getData().getData();
        String id = map.get("id").toString();
        Result player = consumerPlayerService.getPlayer(id);

        Map<String, Object> t = new HashMap<>();
        t.put("user", player.getData());
        MessageData data = new MessageData(msg.getData().getType(),msg.getData().getModel());
        data.setData(t);
        Message message = new Message(msg.getSource(), msg.getTarget(), data);
        return message;
    }


    /**
     * 广场玩家列表
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "广场玩家列表", notes = "广场玩家列表", response = Message.class)
    @RequestMapping("/squareFriend")
    public Message squareFriend(@RequestBody Message msg) {
        log.info("广场玩家列表", JSONObject.toJSONString(msg));
        UserReq jsonReq = DataUtils.getUserReq(msg);
        String condition = jsonReq.getNick();
        PageReq<String> pageReq = new PageReq<>((Map) msg.getData().getData());
        pageReq.setCondition(condition);

        Result players = consumerPlayerService.getPlayers(pageReq);

        Map<String, Object> t = new HashMap<>();
        t.put("userList", players.getData());
        MessageData data = new MessageData(msg.getData().getType(),msg.getData().getModel());
        data.setData(t);
        Message message = new Message(msg.getSource(), msg.getTarget(), data);
        return message;
    }


    /**
     * 忘记密码
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "忘记密码", notes = "忘记密码", response = Message.class)
    @RequestMapping("/pwforget")
    public Message pwforget(@RequestBody Message msg) {
        log.info("忘记密码", JSONObject.toJSONString(msg));
        UserReq jsonReq = DataUtils.getUserReq(msg);
        Result result = consumerPlayerService.forgetPwd(jsonReq.getUsername(), jsonReq.getOldpw());

        Map<String, String> t = new HashMap<>();
        t.put("desc", result.getMsg());
        MessageData data = new MessageData(msg.getData().getType(),msg.getData().getModel());
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
    @ApiOperation(value = "修改密码", notes = "修改密码", response = Message.class)
    @RequestMapping("/expw")
    public Message resetLoginPwd(@RequestBody Message msg) {
        log.info("修改密码", JSONObject.toJSONString(msg));
        UserReq jsonReq = DataUtils.getUserReq(msg);
        Result result = consumerPlayerService.resetLoginPwd(jsonReq.getPlayerId(), jsonReq.getOldpw(), jsonReq.getNewpw());

        log.info("##################### 修改密码 : {}", msg);
        Map<String, String> t = new HashMap<>();
        t.put("desc", result.getMsg());

        MessageData data = new MessageData(msg.getData().getType(),msg.getData().getModel());
        data.setData(t);
        Message message = new Message(msg.getSource(), msg.getTarget(), data);
        return message;
    }

    /**
     * 修改交易密码
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "修改交易密码", notes = "修改交易密码", response = Message.class)
    @RequestMapping("/expwshop")
    public Message expwshop(@RequestBody Message msg) {
        log.info("修改交易密码", JSONObject.toJSONString(msg));
        UserReq jsonReq = DataUtils.getUserReq(msg);
        Result result = consumerPlayerService.resetTraderPwd(jsonReq.getPlayerId(), jsonReq.getOldpw(), jsonReq.getNewpw());
        log.info("##################### 修改交易密码 : {}", msg);
        Map<String, String> t = new HashMap<>();
        t.put("desc", result.getMsg());

        MessageData data = new MessageData(msg.getData().getType(),msg.getData().getModel());
        data.setData(t);
        Message message = new Message(msg.getSource(), msg.getTarget(), data);
        return message;
    }


    /**
     * 用户注册
     *
     * @param message : {
     *        source: clientId,
     *        target: server,
     *        desc:"",
     *        createtime:2019-09-09,
     *        data:{
     *              type: reg,
     *              model: consumer,
     *              data: {
     *                username: wvv,
     *                password: 123456,
     *                nick: wvv1,
     *                invite: 2qwef21,
     *                code: 324512
     *              }
     *        }
     *
     * }
     * @return
     */
    @ApiOperation(value = "用户注册", notes = "用户注册", response = Message.class)
    @RequestMapping("/reg")
    public Message reg(@RequestBody Message message) {
        log.info("用户注册", JSONObject.toJSONString(message));
        Map<String, String> dataInner = new HashMap<>();
        UserReq userReq = DataUtils.getUserReq(message);
        String jsonReq = JSON.toJSONString(userReq);
        Message msg = new Message();
        msg.setSource(message.getTarget());
        msg.setTarget("server");

        if (null == message.getData() || null == message.getData().getData()){
            msg.setDesc("参数错误或不能识别");
            msg.setCreatetime(String.valueOf(System.currentTimeMillis()));
            MessageData messageData =  message.getData();
            dataInner.put("desc","参数错误或不能识别");
            messageData.setData(dataInner);
            msg.setData(messageData);
            return msg;
        }
        MessageData data = new MessageData(message.getData().getType(),message.getData().getModel());
        String jsonData = JsonUtil.parseObjToJson(message.getData().getData());

        JSONObject jsonObject = JSON.parseObject(jsonData);
        String account = jsonObject.getString("username");
        String code = jsonObject.getString("code");
        if (StringUtils.isBlank(account) || StringUtils.isBlank(code)){
            msg.setDesc("参数值不能为空");
            msg.setCreatetime(String.valueOf(System.currentTimeMillis()));
            MessageData messageData =  message.getData();
            dataInner.put("desc","参数值不能为空");
            messageData.setData(dataInner);
            msg.setData(message.getData());
            return msg;
        }
        /*todo************************************************************/
        // TODO [[验证码验证]]
        /*todo************************************************************/
        Result ret = messageService.checkCode(code,account);

        String descT = CityGlobal.Constant.REG_FAIL;
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
                String regSuccess = CityGlobal.Constant.REG_SUCCESS;

                /*todo************************************************************/
                // TODO [[登录或注册成功后保存token]]
                /*todo************************************************************/
                String token = saveToken(userReq.getUsername());
                MessageData messageData =  message.getData();
                dataInner.put("desc","参数值不能为空");

                dataInner.put("token", token);
                dataInner.put("desc", CityGlobal.Constant.REG_SUCCESS);
                data.setData(dataInner);
                msg.setData(data);
                msg.setDesc(regSuccess);


                /*todo************************************************************/
                // TODO [[取出message 中的账户和邀请码]]
                /*todo************************************************************/
                String json = JsonUtil.parseObjToJson(message);
                JSONObject jsonMsg = JSON.parseObject(json);
                JSONObject dataMsg = jsonMsg.getJSONObject("data").getJSONObject("data");
                String invite = dataMsg.getString("invite");
                String username = dataMsg.getString("username");
                //todo 1、取到注册成功的用户信息
                JSONObject jsonObject1 = JSON.parseObject(JsonUtil.parseObjToJson(reg.getData()));
                String playerId = jsonObject1.getString("playerId");
                String playerInvite = jsonObject1.getString("playerCode");

                /*todo************************************************************/
                // TODO [[todo 2、创建用户USDT钱包账户]]
                /*todo************************************************************/
                Result accRet = playerBlockChainService.createBlockChainAccount(username);
                String address = accRet.getData().toString();
                if (!StringUtils.isBlank(address)){
                    playerAccountService.createAccount(playerId,address);
                }

                /*todo************************************************************/
                // TODO [[todo 3、邀请码不为空，设置商会关系,邀请码为空，不设置关系]]
                /*todo************************************************************/
                if(!StringUtils.isBlank(invite)){
                    Result resultParent = consumerPlayerService.getPlayerByInvite(invite);
                    JSONObject parent = JSON.parseObject(JsonUtil.parseObjToJson(resultParent.getData()));
                    String parentId = parent.getString("playerId");

                    //商会关系
                    Result result = treeService.addTree(parentId, playerId, playerInvite);
                    //创建好友关系 待同意
                    friendsService.addFriend(playerId,parentId);
                }
            }else{
                msg.setDesc(reg.getMsg());
                msg.setData(message.getData());
                return  msg;
            }
        }
        msg.setData(message.getData());
        msg.setDesc(ret.getMsg());
        return msg;
    }


    /**
     * 校验验证码
     *
     * @param code
     * @return
     */
    @Deprecated
    private String checkCode(String code, String msgSource) {
        String descMsg = null;
        // 校验验证码
        //验证码不能为空
        if (StringUtils.isBlank(code)) {
            descMsg = CityGlobal.Constant.USER_VLCODE_NULL;
        } else {
            String redisValidCodekey = RedisKeys.REDIS_KEY_VALIDCODE + msgSource;
            //该验证码超时
            if (!redisUtils.hasKey(redisValidCodekey)) {
                descMsg = CityGlobal.Constant.USER_VLCODE_TIMEOUT;
            }
            String redisValidCode = String.valueOf(redisUtils.get(redisValidCodekey));
            //验证码不正确
            if (!code.equalsIgnoreCase(redisValidCode)) {
                descMsg = CityGlobal.Constant.USER_VLCODE_ERROR;
            }
        }
        return descMsg;
    }


    /**
     * 密码登录
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "密码登录", notes = "密码登录", response = Message.class)
    @RequestMapping("/pwlogoin")
    public Message pwLogoin(@RequestBody Message msg) {
        log.info("密码登录", JSONObject.toJSONString(msg));
        UserReq userReq = DataUtils.getUserReq(msg);
        String jsonReq = JSON.toJSONString(userReq);

        Result result = consumerPlayerService.pwLogoin(jsonReq);
        log.info("##################### 用户登录: {}", result);

        Map<String, String> t = new HashMap<>();
        String descT = CityGlobal.Constant.LOGIN_FAIL;
        if (result.getSuccess()) {
            descT = CityGlobal.Constant.LOGIN_SUCCESS;

            String token = saveToken(userReq.getUsername());
            t.put("token", token);
        }

        MessageData data = new MessageData("pwlog", "consumer");
        t.put("desc", descT);
        data.setData(t);
        Message message = new Message(msg.getSource(), msg.getTarget(), data);
        message.setSource(msg.getSource());
        message.setTarget(msg.getTarget());
        message.setDesc(result.getMsg());
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
    @ApiOperation(value = "验证码登录", notes = "验证码登录", response = Message.class)
    @RequestMapping("/codelogoin")
    public Message codeLogoin(@RequestBody Message msg) {
        log.info("验证码登录", JSONObject.toJSONString(msg));
        Message message = new Message();
        message.setSource(msg.getSource());
        message.setTarget(msg.getTarget());
        MessageData data = new MessageData("idlog", "consumer");
        Map<String, String> t = new HashMap<>();

        UserReq userReq = DataUtils.getUserReq(msg);
        // 校验认证码
        String descMsg = checkCode(userReq.getCode(), msg.getSource());
        String descT = CityGlobal.Constant.LOGIN_FAIL;

        if (StringUtils.isBlank(descMsg)) {
            Result idlog = consumerPlayerService.codeLogoin(JSON.toJSONString(userReq));
            log.info("##################### 验证码登录: {}", idlog);
            descMsg = idlog.getMsg();

            if (idlog.getSuccess()) {
                descT = CityGlobal.Constant.LOGIN_SUCCESS;

                String token = saveToken(userReq.getUsername());
                t.put("token", token);
            }
        }

        t.put("desc", descT);
        data.setData(t);
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
    @ApiOperation(value = "登出", notes = "登出", response = Message.class)
    @RequestMapping("/exit")
    public Message exit(@RequestBody Message msg) {
        log.info("登出", JSONObject.toJSONString(msg));
        UserReq jsonReq = DataUtils.getUserReq(msg);
        Result result = consumerPlayerService.quit(jsonReq.getPlayerId());
        log.info("##################### 用户登出 :{}", msg);

        String redisKey = RedisKeys.LOGIN_USER_TOKEN + jsonReq.getUsername();
        if (redisUtils.hasKey(redisKey)) {
            redisUtils.del(redisKey);
        }

        redisKey = RedisKeys.SQUARE_PLAYER_LIST_ANOTHER_BATCH + jsonReq.getUsername();
        redisUtils.del(redisKey);

        Map<String, String> t = new HashMap<>();
        t.put("desc", result.getMsg());
        MessageData data = new MessageData(msg.getData().getType(),msg.getData().getModel());
        data.setData(t);
        Message message = new Message(msg.getSource(), msg.getTarget(), data);
        return message;
    }




}