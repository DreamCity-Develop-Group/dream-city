package com.dream.city.service.handler.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.*;
import com.dream.city.base.model.entity.*;
import com.dream.city.base.model.enu.*;
import com.dream.city.base.model.req.FriendsReq;
import com.dream.city.base.model.req.PlayerAccountReq;
import com.dream.city.base.model.req.PlayerReq;
import com.dream.city.base.model.req.UserReq;
import com.dream.city.base.model.resp.PlayerAccountResp;
import com.dream.city.base.model.resp.PlayerResp;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.base.utils.RedisKeys;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.consumer.*;
import com.dream.city.service.handler.CommonService;
import com.dream.city.service.handler.PlayerService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {
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
    ConsumerDictionaryService dictionaryService;
    @Autowired
    ConsumerPlayerService playerService;
    @Autowired
    ConsumerAccountService accountService;
    @Autowired
    ConsumerTradeService tradeService;

    @Autowired
    CommonService commonService;
    /**
     * 修改玩家头像
     *
     * @param msg
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Message updatePlayerHeadImg(Message msg) throws BusinessException {
        log.info("修改玩家头像", JSONObject.toJSONString(msg));
        UserReq jsonReq = DataUtils.getUserReq(msg);
        PlayerResp player = commonService.getPlayerByUserName(msg);
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


    @LcnTransaction
    @Transactional
    @Override
    public Message searchfriend(Message msg) throws BusinessException {
        log.info("广场玩家列表 换一批", JSONObject.toJSONString(msg));

        UserReq jsonReq = DataUtils.getUserReq(msg);
        PlayerReq playerReq = new PlayerReq();

        String playerId = jsonReq.getPlayerId();
        String nick = jsonReq.getNick();
        String playerName = null;
        PlayerResp playerByStrUserName = commonService.getPlayerByStrUserName(nick);
        if (playerByStrUserName != null) {
            playerName = playerByStrUserName.getPlayerNick();
            nick = null;
        }
        if (StringUtils.isNotBlank(nick)) {
            playerReq.setPlayerNick(nick);
            playerReq.setPlayerName(playerName);
        }
        if (StringUtils.isBlank(jsonReq.getPlayerId())) {
            msg.setDesc("当前玩家id不能为空");
            msg.getData().setCode(ReturnStatus.PARAM_ERROR.getStatus());
            return msg;
        }
        playerReq.setPlayerId(playerId);

        Integer pageSize = jsonReq.getPageSize() == null ? 20 : jsonReq.getTotal();
        Integer pageNum = jsonReq.getPageNum() == null ? 0 : jsonReq.getPageNum();
        String redisKey = RedisKeys.SQUARE_PLAYER_LIST_ANOTHER_BATCH + playerId;
        if (redisUtils.hasKey(redisKey)) {
            Long incr = redisUtils.incr(redisKey);
            if (incr != null) {
                pageNum = Integer.parseInt(String.valueOf(incr));
            }
        } else {
            redisUtils.incr(redisKey);
            pageNum++;
        }
        Integer playersCount = consumerPlayerService.getPlayersCount(playerReq);
        int pNum = (playersCount % pageSize) > 0 ? (playersCount / pageSize + 1) : (playersCount / pageSize);
        if (pageNum > pNum) {
            pageNum = pNum;
            redisUtils.del(redisKey);
        }

        Page pageReq = new Page();
        pageReq.setTotal(playersCount);
        pageReq.setPageSize(pageSize);
        pageReq.setPageNum(pageNum);
        pageReq.setCount(Boolean.TRUE);
        pageReq.setCondition(playerReq);

        Result<PageInfo> players = consumerPlayerService.getPlayers(pageReq);
        if (players.getSuccess() && players.getData() != null) {
            List<Map> dataList = new ArrayList<>();
            PageInfo<PlayerResp> pageInfo = players.getData();
            List<PlayerResp> playerList = pageInfo.getList();
            if (!CollectionUtils.isEmpty(playerList)) {
                Map data = null;
                for (int i = 0; i < playerList.size(); i++) {
                    PlayerResp resp = DataUtils.getData(playerList.get(i), PlayerResp.class);
                    data = new HashMap();
                    data.put("playerId", StringUtils.isBlank(resp.getPlayerId()) ? "" : resp.getPlayerId());
                    data.put("imgurl", StringUtils.isBlank(resp.getImgurl()) ? "" : resp.getImgurl());
                    data.put("friendId", StringUtils.isBlank(resp.getFriendId()) ? "" : resp.getFriendId());
                    data.put("nick", StringUtils.isBlank(resp.getPlayerNick()) ? "" : resp.getPlayerNick());
                    data.put("agree", resp.getAgree() == null ? -1 : resp.getAgree());
                    data.put("grade", StringUtils.isBlank(resp.getGrade()) ? "" : resp.getGrade());

                    dataList.add(data);
                }
            }

            PageInfo pageRsult = pageInfo;
            pageRsult.getList().clear();
            pageRsult.setList(dataList);


            msg.setDesc(players.getMsg());
            msg.setCode(ReturnStatus.SUCCESS.getStatus());
            msg.getData().setCode(ReturnStatus.SUCCESS.getStatus());
            msg.getData().setData(pageRsult);
        } else {
            msg.setDesc("未查询到数据");
            msg.getData().setCode(ReturnStatus.SUCCESS.getStatus());
        }

        return msg;
    }

    @LcnTransaction
    @Transactional
    @Override
    public Message setTradePass(Message msg) throws BusinessException {
        log.info("设置交易密码", JSONObject.toJSONString(msg));

        String json = JsonUtil.parseObjToJson(msg.getData().getData());
        JSONObject data = JsonUtil.parseJsonToObj(json, JSONObject.class);

        String tradePass = data.getString("tradePass");
        String playerId = data.getString("playerId");
        Player player = playerService.getPlayerByPlayerId(playerId);

        Result result = consumerPlayerService.setTradePassword(player);

        msg.getData().setData(result.getData());
        msg.getData().setCode(result.getCode());

        return msg;
    }


    /**
     * 获取认证码
     *
     * @param msg
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Message getCode(Message msg) throws BusinessException {
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
    @LcnTransaction
    @Transactional
    @Override
    public Message getUser(Message msg) throws BusinessException {
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
    @LcnTransaction
    @Transactional
    @Override
    public Message squareFriends(Message msg) throws BusinessException {
        log.info("广场玩家列表", JSONObject.toJSONString(msg));
        UserReq jsonReq = DataUtils.getUserReq(msg);
        //当前用户id
        String playerId = jsonReq.getPlayerId();
        //查询用户
        //String username = jsonReq.getUsername();
        String nick = jsonReq.getNick();

        PlayerResp playerByStrUserName = commonService.getPlayerByStrUserName(nick);
        PlayerResp playerByStrNick = commonService.getPlayerByStrNick(nick);
        if (playerByStrUserName != null) {
            nick = playerByStrUserName.getPlayerNick();
        }
        if (playerByStrNick != null) {
            nick = playerByStrNick.getPlayerNick();
        }

        PlayerReq playerReq = new PlayerReq();
        playerReq.setPlayerId(playerId);
        playerReq.setPlayerNick(nick);

        Page pageReq = new Page();
        pageReq.setCondition(playerReq);
        Result<PageInfo> players = consumerPlayerService.getPlayers(pageReq);

        List<Map> dataList = new ArrayList<>();
        PageInfo<PlayerResp> pageInfo = players.getData();
        if (pageInfo != null && !CollectionUtils.isEmpty(pageInfo.getList())) {
            Map data = null;
            for (int i = 0; i < pageInfo.getList().size(); i++) {
                PlayerResp resp = DataUtils.getData(pageInfo.getList().get(i), PlayerResp.class);
                data = new HashMap();
                data.put("playerId", StringUtils.isBlank(resp.getPlayerId()) ? "" : resp.getPlayerId());
                data.put("imgurl", StringUtils.isBlank(resp.getImgurl()) ? "" : resp.getImgurl());
                data.put("friendId", StringUtils.isBlank(resp.getFriendId()) ? "" : resp.getFriendId());
                data.put("nick", StringUtils.isBlank(resp.getPlayerNick()) ? "" : resp.getPlayerNick());
                //-1未申请,0已申请,1已同意
                data.put("agree", resp.getAgree() == null ? -1 : resp.getAgree());
                data.put("grade", StringUtils.isBlank(resp.getGrade()) ? "" : resp.getGrade());

                dataList.add(data);
            }
        }

        PageInfo pageRsult = pageInfo;
        pageRsult.getList().clear();
        pageRsult.setList(dataList);

        msg.setDesc(players.getMsg());
        msg.getData().setData(pageRsult);
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
    @LcnTransaction
    @Transactional
    @Override
    public Message pwforget(Message msg) throws BusinessException {
        log.info("忘记密码", JSONObject.toJSONString(msg));
        UserReq jsonReq = DataUtils.getUserReq(msg);
        Result result = consumerPlayerService.forgetPwd(jsonReq.getUsername(), jsonReq.getNewpw());

        return Message.generateMessage(msg,result);
    }

    /**
     * 修改密码
     *
     * @param msg
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Message resetLoginPass(Message msg) throws BusinessException {
        log.info("修改密码", JSONObject.toJSONString(msg));
        UserReq jsonReq = DataUtils.getUserReq(msg);
        Result result = consumerPlayerService.resetLoginPwd(jsonReq.getUsername(), jsonReq.getOldpw(), jsonReq.getNewpw());

        log.info("##################### 修改密码 : {}", msg);
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("desc", result.getMsg());

        MessageData data = new MessageData(msg.getData().getType(), msg.getData().getModel());
        //添加判断状态码
        data.setCode(result.getCode());
        if (result.getSuccess()) {
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
    @LcnTransaction
    @Transactional
    @Override
    public Message resetTradePass(Message msg) throws BusinessException {
        log.info("修改交易密码", JSONObject.toJSONString(msg));
        UserReq jsonReq = DataUtils.getUserReq(msg);
        PlayerResp player = commonService.getPlayerByUserName(msg);
        String username = jsonReq.getUsername();
        String oldPwd = jsonReq.getOldpwshop();
        String newPwd = jsonReq.getNewpwshop();

        Result result;
        if (StringUtils.isBlank(newPwd)) {
            msg.getData().setCode(ReturnStatus.PARAM_ERROR.getStatus());
            msg.setDesc("新密码不能为空 ！");
            return msg;
        } else if (StringUtils.isBlank(oldPwd)) {
            oldPwd = "";
            result = consumerPlayerService.resetTraderPwd(username, oldPwd, newPwd);

        } else {
            result = consumerPlayerService.resetTraderPwd(username, oldPwd, newPwd);
        }

        log.info("##################### 修改交易密码 : {}", msg);
        Map<String, String> t = new HashMap<>();
        t.put("desc", result.getMsg());

        if (result.getSuccess() && StringUtils.isNotBlank(oldPwd)) {
            if (StringUtils.isNotBlank(player.getPlayerTradePass())) {
                //修改交易密码扣除MT
                String valByKey = dictionaryService.getValByKey("player.change.tran.pwd.tax");
                BigDecimal taxMt = BigDecimal.valueOf(Double.parseDouble(valByKey));

                //玩家账户
                PlayerAccount playerAccount = playerAccountService.getPlayerAccount(player.getPlayerId());

                //平台账户
                String plafortAddr = dictionaryService.getValByKey("platform.account.accIds");
                PlayerAccountReq plafortAccountReq = new PlayerAccountReq();
                plafortAccountReq.setAccAddr(plafortAddr);
                Result<PlayerAccountResp> plafortAccountResult = accountService.getPlayerAccounts(plafortAccountReq);

                //扣除玩家MT
                Result<Integer> updatePlayerAccount = null;
                if (playerAccount != null && plafortAccountResult != null && plafortAccountResult.getSuccess()) {
                    BigDecimal mtSurplus = playerAccount.getAccMt().subtract(taxMt);

                    //更新玩家账户
                    PlayerAccount updateAccount = new PlayerAccount();
                    updateAccount.setAccId(playerAccount.getAccId());
                    updateAccount.setAccMt(mtSurplus);
                    updateAccount.setAccMtAvailable(mtSurplus);
                    updateAccount.setAccPlayerId(playerAccount.getAccPlayerId());
                    updatePlayerAccount = accountService.updatePlayerAccount(updateAccount);

                    //玩家交易记录
                    PlayerTrade tradeReq = new PlayerTrade();
                    tradeReq.setTradeAmount(taxMt);
                    tradeReq.setTradePlayerId(playerAccount.getAccPlayerId());
                    tradeReq.setTradeStatus(TradeStatus.OUT.getCode());
                    tradeReq.setTradeType(TradeType.CHANGE_TRAN_PWD.getCode());
                    tradeReq.setInOutStatus(AmountDynType.OUT.getCode());
                    tradeReq.setTradeDesc("玩家修改交易密码，玩家账户扣除[" + taxMt + "]MT");
                    Result<PlayerTrade> tradeResult = tradeService.insertPlayerTrade(tradeReq);

                    //玩家交易流水
                    TradeDetail tradeDetailReq = new TradeDetail();
                    tradeDetailReq.setUsdtSurplus(playerAccount.getAccUsdt());
                    tradeDetailReq.setMtSurplus(mtSurplus);
                    tradeDetailReq.setTradeAmount(taxMt);
                    tradeDetailReq.setTradeDetailType(TradeDetailType.CHANGE_TRAN_PWD.getCode());
                    tradeDetailReq.setPlayerId(playerAccount.getAccPlayerId());
                    tradeDetailReq.setDescr("玩家修改交易密码，玩家账户扣除[" + taxMt + "]MT");
                    if (tradeResult != null && tradeResult.getSuccess() && tradeResult.getData() != null) {
                        tradeDetailReq.setTradeId(tradeResult.getData().getTradeId());
                    }
                    tradeService.insertTradeDetail(tradeDetailReq);
                }

                //平台账户增加MT
                if (updatePlayerAccount != null && updatePlayerAccount.getSuccess()
                        && plafortAccountResult.getSuccess() && plafortAccountResult.getData() != null) {
                    //更新平台账户
                    PlayerAccount updateAccount = new PlayerAccount();
                    updateAccount.setAccId(plafortAccountResult.getData().getAccId());
                    updateAccount.setAccMt(plafortAccountResult.getData().getAccMt().add(taxMt));
                    updateAccount.setAccMtAvailable(plafortAccountResult.getData().getAccMtAvailable().add(taxMt));
                    updateAccount.setAccPlayerId(plafortAccountResult.getData().getPlayerId());
                    accountService.updatePlayerAccount(updateAccount);

                    //平台交易记录
                    PlayerTrade tradeReq = new PlayerTrade();
                    tradeReq.setTradeAmount(taxMt);
                    tradeReq.setTradePlayerId(plafortAccountResult.getData().getPlayerId());
                    tradeReq.setTradeStatus(TradeStatus.IN.getCode());
                    tradeReq.setTradeType(TradeType.CHANGE_TRAN_PWD.getCode());
                    tradeReq.setInOutStatus(AmountDynType.IN.getCode());
                    tradeReq.setTradeDesc("玩家修改交易密码，平台账户收取玩家[" + taxMt + "]MT");
                    Result<PlayerTrade> tradeResult = tradeService.insertPlayerTrade(tradeReq);

                    //平台交易流水
                    TradeDetail tradeDetailReq = new TradeDetail();
                    tradeDetailReq.setUsdtSurplus(plafortAccountResult.getData().getAccUsdt());
                    tradeDetailReq.setMtSurplus(plafortAccountResult.getData().getAccMt().add(taxMt));
                    tradeDetailReq.setTradeAmount(taxMt);
                    tradeDetailReq.setTradeDetailType(TradeDetailType.CHANGE_TRAN_PWD.getCode());
                    tradeDetailReq.setPlayerId(plafortAccountResult.getData().getPlayerId());
                    tradeDetailReq.setDescr("玩家修改交易密码，平台账户收取玩家[" + taxMt + "]MT");
                    if (tradeResult != null && tradeResult.getSuccess() && tradeResult.getData() != null) {
                        tradeDetailReq.setTradeId(tradeResult.getData().getTradeId());
                    }
                    tradeService.insertTradeDetail(tradeDetailReq);
                }
            }
        }

        MessageData data = new MessageData(msg.getData().getType(), msg.getData().getModel());
        data.setData(t);
        data.setCode(result.getCode());
        Message message = new Message(msg.getSource(), msg.getTarget(), data);
        message.setCode(result.getCode());
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
    @LcnTransaction
    @Transactional
    @Override
    public Message reg(Message message) {
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
                //获取钱包地址
                Result accRet = playerBlockChainService.createBlockChainAccount(username);
                if (accRet.getSuccess()) {
                    String address = accRet.getData().toString();
                    log.info("玩家账户地址：[" + address + "]创建成功");
                    if (!StringUtils.isBlank(address)) {
                        Result create = playerAccountService.createAccount(playerId, address);
                        if (create.getSuccess()) {
                            log.info("玩家账户创建成功");
                        } else {
                            log.info("玩家账户创建失败，重试一次");
                            playerAccountService.createAccount(playerId, address);
                        }
                    }
                } else {
                    log.info("获取钱包地址不成功，重试一次");
                    accRet = playerBlockChainService.createBlockChainAccount(username);
                    if (accRet.getSuccess()) {
                        String address = accRet.getData().toString();
                        log.info("玩家账户地址：[" + address + "]获取成功");
                        if (!StringUtils.isBlank(address)) {
                            Result create = playerAccountService.createAccount(playerId, address);
                            if (create.getSuccess()) {
                                log.info("玩家账户创建成功");
                            }
                        }
                    } else {
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
                    FriendsReq friendsReq = new FriendsReq();
                    friendsReq.setPlayerId(playerId);
                    friendsReq.setFriendId(parentId);
                    friendsReq.setInvite(invite);
                    Result<Boolean> addFriend = friendsService.addFriend(friendsReq);

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
                map.put("token", token);
                msg.setDesc(regSuccess);
                msg.getData().setCode(ReturnStatus.SUCCESS.getStatus());
                msg.getData().setData(map);
                end = System.currentTimeMillis();
                return msg;
            } else {
                //注册失败
                msg.getData().setCode(reg.getCode());
                msg.setDesc(reg.getMsg());
                end = System.currentTimeMillis();
                return msg;
            }
        } else {
            //验证码验证不通过
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
    @LcnTransaction
    @Transactional
    @Override
    public Message login(Message msg) throws BusinessException {
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
    @LcnTransaction
    @Transactional
    @Override
    public Message codeLogin(Message msg) throws BusinessException {
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
        int code = ReturnStatus.FAILED.getStatus();
        Map<String, String> ret = new HashMap<>();
        if (checkRet.getSuccess()) {
            Result idlog = consumerPlayerService.codeLogin(JSON.toJSONString(userReq));
            log.info("##################### 验证码登录: {}", idlog);
            descMsg = idlog.getMsg();

            if (idlog.getSuccess()) {
                log.info("验证码登录成功");

                String playerId = idlog.getData().toString();

                Result playerRet = consumerPlayerService.getPlayer(playerId);
                Player player = JsonUtil.parseJsonToObj(playerRet.getData().toString(), Player.class);
                String token = saveToken(userReq.getUsername());
                ret.put("token", token);
                ret.put("playerId", playerId);
                ret.put("id", player.getId().toString());
                ret.put("playerName", player.getPlayerName());
                code = ReturnStatus.SUCCESS.getStatus();
            } else {
                log.info("验证码登录失败");
            }
        }
        data.setCode(code);
        data.setData(ret);
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
    @LcnTransaction
    @Transactional
    @Override
    public Message exit(Message msg) throws BusinessException {
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


    @LcnTransaction
    @Transactional
    @Override
    public Message readMessage(Message msg) throws BusinessException {
        CityMessage message = getCityMessage(msg);
        Result<CityMessage> result = messageService.getMessageById(message.getId());
        Map map = new HashMap();
        if (result != null) {
            CityMessage cityMessage = result.getData();
            if (cityMessage != null) {
                /*map.put("id",cityMessage.getId());
                map.put("title", cityMessage.getTitle());
                map.put("createtime",cityMessage.getCreateTime());
                map.put("content",cityMessage.getContent());
                map.put("readState",true);*/

                cityMessage.setHaveRead(1);
                messageService.updateMessageHaveReadById(cityMessage);

                Result<Integer> integerResult = messageService.getUnReadCount(cityMessage.getPlayerId());
                boolean messages = true;
                if (integerResult != null) {
                    int count = integerResult.getData();
                    messages = count > 0 ? true : false;
                }
                map.put("messages", messages);
                msg.setCode(ReturnStatus.SUCCESS.getStatus());
                msg.getData().setCode(ReturnStatus.SUCCESS.getStatus());
            }
        }
        msg.setDesc(result.getMsg());
        msg.getData().setData(map);
        return msg;
    }


    @LcnTransaction
    @Transactional
    @Override
    public Message getMessageList(Message msg) throws BusinessException {
        CityMessage message = getCityMessage(msg);
        Result<List<CityMessage>> result = messageService.getCityMessageList(message);

        List<Map> resultList = new ArrayList<>();
        if (result != null) {
            List<CityMessage> messageList = result.getData();
            if (!CollectionUtils.isEmpty(messageList)) {
                Map map = null;
                for (CityMessage cityMessage : messageList) {
                    map = new HashMap();
                    map.put("id", cityMessage.getId());
                    map.put("title", cityMessage.getTitle());
                    map.put("createtime", cityMessage.getCreateTime());
                    map.put("content", cityMessage.getContent());
                    map.put("readState", cityMessage.getHaveRead() == 0 ? false : true);
                    resultList.add(map);
                }
            }
        }
        Result<Integer> integerResult = messageService.getUnReadCount(message.getPlayerId());
        boolean messages = true;
        if (integerResult != null) {
            int count = integerResult.getData();
            messages = count > 0 ? true : false;
        }
        Map resultData = new HashMap();
        resultData.put("messageList", resultList);
        resultData.put("messages", messages);
        msg.setDesc(result.getMsg());
        msg.getData().setData(resultData);
        return msg;
    }


    private CityMessage getCityMessage(Message msg) {
        JSONObject jsonObject = JsonUtil.parseJsonToObj(JsonUtil.parseObjToJson(msg.getData().getData()), JSONObject.class);
        Long id = Long.parseLong(jsonObject.containsKey("id") ? String.valueOf(jsonObject.get("id")) : "0");
        String playerId = jsonObject.containsKey("playerId") ? String.valueOf(jsonObject.get("playerId")) : null;
        String friendId = jsonObject.containsKey("friendId") ? String.valueOf(jsonObject.get("friendId")) : null;
        String title = jsonObject.containsKey("title") ? String.valueOf(jsonObject.get("title")) : null;
        String haveReadStr = jsonObject.containsKey("readState") ? String.valueOf(jsonObject.get("readState")) : null;
        Boolean haveRead = StringUtils.isBlank(haveReadStr) ? null : Boolean.parseBoolean(haveReadStr);
        id = id == 0 ? null : id;
        CityMessage message = new CityMessage();
        message.setHaveRead(haveRead == null ? null : (haveRead.equals(Boolean.TRUE) ) ? 1 : 0);
        message.setTitle(title);
        message.setPlayerId(playerId);
        message.setFriendId(friendId);
        message.setId(id);
        return message;
    }
}
