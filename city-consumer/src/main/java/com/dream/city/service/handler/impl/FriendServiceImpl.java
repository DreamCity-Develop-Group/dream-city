package com.dream.city.service.handler.impl;

import com.alibaba.fastjson.JSONObject;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Page;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerLikes;
import com.dream.city.base.model.req.FriendsReq;
import com.dream.city.base.model.resp.FriendsResp;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.service.consumer.ConsumerFriendsService;
import com.dream.city.service.consumer.ConsumerPlayerService;
import com.dream.city.service.handler.FriendService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wvv
 * @program: dream-city
 * @File: FriendServiceImpl
 * @description: 好友服务逻辑
 * @create: 2019/10/2019/10/27 22:34:53 [星期日]
 **/
@Service
public class FriendServiceImpl implements FriendService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ConsumerFriendsService consumerFriendsService;
    @Autowired
    ConsumerPlayerService consumerPlayerService;


    /**
     * 好友主页
     *
     * @param msg
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Message friendHomePage(Message msg) throws BusinessException {
        String json = JSONObject.toJSONString(msg);

        logger.info("好友主页", json);
        //return orderHandleService.getPlayerInvestOrders(msg);
        String data = JsonUtil.parseObjToJson(msg.getData());
        JSONObject jsonObject = JsonUtil.parseJsonToObj(data, JSONObject.class);
        String playerId = jsonObject.getString("playerId");

        Result<List<PlayerLikes>> result = consumerFriendsService.getInvestLikes(playerId);


        return Message.generateMessage(msg, result);
    }


    @LcnTransaction
    @Transactional
    @Override
    public Message addFriend(Message msg) throws BusinessException {
        logger.info("添加好友", JSONObject.toJSONString(msg));
        Map map = getPlayerIdOrFriendId(msg);
        String playerId = map.containsKey("playerId") ? (String) map.get("playerId") : null;
        String friendId = map.containsKey("friendId") ? (String) map.get("friendId") : null;
        String invite = map.containsKey("invite") ? (String) map.get("invite") : null;

        FriendsReq friendsReq = new FriendsReq();
        friendsReq.setPlayerId(playerId);
        friendsReq.setFriendId(friendId);
        friendsReq.setInvite(invite);
        Result<Boolean> b = consumerFriendsService.addFriend(friendsReq);
        Message message = getResultMessage(b.getSuccess(), "添加好友", msg);
        return message;
    }

    @LcnTransaction
    @Transactional
    @Override
    public Message agreeApply(Message msg) throws BusinessException {
        logger.info("通过好友", JSONObject.toJSONString(msg));
        Map map = getPlayerIdOrFriendId(msg);
        String playerId = map.containsKey("playerId") ? (String) map.get("playerId") : null;
        String friendId = map.containsKey("friendId") ? (String) map.get("friendId") : null;
        String playerNick = map.containsKey("playerNick") ? (String) map.get("playerNick") : null;
        String agree = map.containsKey("agree") ? (String) map.get("agree") : "disagreed";

        Result<Boolean> b = consumerFriendsService.agreeAddFriend(playerId, friendId, agree);
        /*if (b.getData() && "agree".equalsIgnoreCase(agree)){
            commonsService.sendMessage(playerId,friendId,"[" + playerNick + "]已经通过了你的好友申请。");
        }
        if (b.getData() && "disagreed".equalsIgnoreCase(agree)){
            commonsService.sendMessage(playerId,friendId,"[" + playerNick + "]已经拒绝了你的好友申请。");
        }*/
        Message message = getResultMessage(b.getSuccess(), "通过好友", msg);
        return message;
    }

    @LcnTransaction
    @Transactional
    @Override
    public Message friendList(Message msg) throws BusinessException {
        logger.info("获取好友列表", JSONObject.toJSONString(msg));
        Message message = new Message();
        MessageData data = new MessageData(msg.getData().getType(), msg.getData().getModel());
        String desc = "获取好友成功";
        List<Map> dataList = new ArrayList<>();
        try {
            FriendsReq condition = DataUtils.getFriendsReq(msg);
            Page pageReq = new Page<>();
            pageReq.setCondition(condition);
            pageReq.setPageSize(9999999);
            Result<PageInfo<FriendsResp>> page = consumerFriendsService.friendList(pageReq);
            PageInfo<FriendsResp> pageInfo = page.getData();
            if (pageInfo != null) {
                List<FriendsResp> friendList = pageInfo.getList();
                if (!CollectionUtils.isEmpty(friendList)) {
                    Map map = null;
                    for (FriendsResp resp : friendList) {
                        map = new HashMap();
                        map.put("playerId", StringUtils.isBlank(resp.getPlayerId()) ? "" : resp.getPlayerId());
                        map.put("imgurl", StringUtils.isBlank(resp.getFriendImgurl()) ? "" : resp.getFriendImgurl());
                        map.put("friendId", StringUtils.isBlank(resp.getFriendId()) ? "" : resp.getFriendId());
                        map.put("nick", StringUtils.isBlank(resp.getFriendNick()) ? "" : resp.getFriendNick());
                        //-1未申请,0已申请,1已同意
                        map.put("agree", resp.getAgree() == null ? -1 : resp.getAgree());
                        map.put("grade", StringUtils.isBlank(resp.getGrade()) ? "" : resp.getGrade());
                        dataList.add(map);
                    }
                }

            }

            PageInfo pageRsult = pageInfo;
            pageRsult.getList().clear();
            pageRsult.setList(dataList);

            data.setData(pageRsult);
        } catch (Exception e) {
            desc = "获取好友失败";
            logger.error(desc, e);
        }
        message.setData(data);
        message.setDesc(desc);
        return message;
    }

    @LcnTransaction
    @Transactional
    @Override
    public Message applyFriend(Message msg) throws BusinessException {
        logger.info("获取好友申请列表", JSONObject.toJSONString(msg));
        Message message = new Message();
        MessageData data = new MessageData(msg.getData().getType(), msg.getData().getModel());
        String desc = "获取好友申请列表成功";
        List<Map> dataList = new ArrayList<>();
        try {
            FriendsReq condition = DataUtils.getFriendsReq(msg);
            Page pageReq = new Page<>();
            pageReq.setCondition(condition);
            pageReq.setPageSize(9999999);
            Result<PageInfo<FriendsResp>> page = consumerFriendsService.applyFriendList(pageReq);
            PageInfo<FriendsResp> pageInfo = page.getData();
            if (pageInfo != null) {
                List<FriendsResp> friendList = pageInfo.getList();
                if (!CollectionUtils.isEmpty(friendList)) {
                    Map map = null;
                    for (FriendsResp resp : friendList) {
                        map = new HashMap();
                        map.put("playerId", StringUtils.isBlank(resp.getPlayerId()) ? "" : resp.getPlayerId());
                        map.put("imgurl", StringUtils.isBlank(resp.getFriendImgurl()) ? "" : resp.getFriendImgurl());
                        map.put("friendId", StringUtils.isBlank(resp.getFriendId()) ? "" : resp.getFriendId());
                        map.put("nick", StringUtils.isBlank(resp.getFriendNick()) ? "" : resp.getFriendNick());
                        //-1未申请,0已申请,1已同意
                        map.put("agree", resp.getAgree() == null ? -1 : resp.getAgree());
                        map.put("grade", StringUtils.isBlank(resp.getGrade()) ? "" : resp.getGrade());
                        dataList.add(map);
                    }
                }

            }

            PageInfo pageRsult = pageInfo;
            pageRsult.getList().clear();
            pageRsult.setList(dataList);

            data.setData(pageRsult);
        } catch (Exception e) {
            desc = "获取好友申请列表失败";
            logger.error(desc, e);
        }
        message.setData(data);
        message.setDesc(desc);
        return message;
    }


    /**
     * Message
     *
     * @param b
     * @param desc
     * @return
     */
    private Message getResultMessage(boolean b, String desc, Message msg) {
        Message message = new Message();
        MessageData<Map> data = new MessageData(msg.getData().getType(), msg.getData().getModel());
        String agree = "disagreed";
        if (b) {
            desc = desc + "成功";
            agree = "agreed";
        } else {
            desc = desc + "失败";
        }
        Map map = new HashMap();
        map.put("agree", agree);
        data.setData(map);
        message.setData(data);
        message.setDesc(desc);
        return message;
    }


    /**
     * 从入参中获取playerId、friendId
     *
     * @param msg
     * @return
     */
    private Map getPlayerIdOrFriendId(Message msg) {
        Map map = (Map) msg.getData().getData();

        String agree = map.containsKey("agree") ? (String) map.get("agree") : null;
        String playerName = map.containsKey("playerName") ? (String) map.get("playerName") : null;
        String playerNick = map.containsKey("playerNick") ? (String) map.get("playerNick") : null;
        if (StringUtils.isBlank(playerName)) {
            playerName = map.containsKey("username") ? (String) map.get("username") : null;
        }
        String playerId = map.containsKey("playerId") ? (String) map.get("playerId") : null;
        /*if (StringUtils.isNotBlank(playerName)){
            JSONObject playerNamejsonObject = new JSONObject();
            playerNamejsonObject.put("playerName",playerName);
            Result<String> player = consumerPlayerService.getPlayerByName(playerNamejsonObject.toJSONString());
            Map<String,Object> playerMap = JSON.parseObject(player.getData(),Map.class);
            //playerId = playerMap.containsKey("playerId")?(String)playerMap.get("playerId"):null;
            playerNick = playerMap.containsKey("playerNick")?(String)playerMap.get("playerNick"):null;
        }*/

        String nick = map.containsKey("nick") ? (String) map.get("nick") : null;
        String friendId = map.containsKey("friendId") ? (String) map.get("friendId") : null;
        /*if (StringUtils.isNotBlank(nick)){
            JSONObject nickjsonObject = new JSONObject();
            nickjsonObject.put("playerNick",nick);
            Result<String> friend = consumerPlayerService.getPlayerByName(nickjsonObject.toJSONString());
            Map<String,Object> friendMap = JSON.parseObject(friend.getData(),Map.class);
            friendId = friendMap.containsKey("playerId")?(String)friendMap.get("playerId"):null;
        }*/
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("playerId", playerId);
        resultMap.put("username", playerName);
        resultMap.put("playerNick", playerNick);
        resultMap.put("friendId", friendId);
        resultMap.put("nick", nick);
        resultMap.put("agree", agree);
        return resultMap;
    }
}
