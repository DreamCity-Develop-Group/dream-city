package com.dream.city.service.routes.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.req.UserReq;
import com.dream.city.base.model.resp.PlayerResp;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.service.consumer.ConsumerAccountService;
import com.dream.city.service.consumer.ConsumerCommonsService;
import com.dream.city.service.consumer.ConsumerPlayerService;
import com.dream.city.service.consumer.ConsumerTreeService;
import com.dream.city.service.routes.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private ConsumerCommonsService commonsService;
    @Autowired
    private ConsumerAccountService accountService;
    @Autowired
    ConsumerPlayerService playerService;
    @Autowired
    ConsumerTreeService treeService;

    @LcnTransaction
    @Transactional
    @Override
    public Message getPlayerAccount(Message msg) throws BusinessException {
        log.info("获取玩家账户", JSONObject.toJSONString(msg));
        Object dataMsg = msg.getData().getData();
        JSONObject jsonObject = JsonUtil.parseJsonToObj(JsonUtil.parseObjToJson(dataMsg), JSONObject.class);
        String username = jsonObject.getString("username");
        String token = jsonObject.getString("token");
        String playerId = jsonObject.getString("playerId");

        Player player1 = playerService.getPlayerByPlayerId(playerId);
        Object relationTree = treeService.getTree(playerId).getData();
        int size = 0;
        int total = 0;
        if(relationTree!=null) {
            Result trees = treeService.getMembers(playerId, 9);
            JSONObject tree = JsonUtil.parseJsonToObj(JsonUtil.parseObjToJson(trees.getData()), JSONObject.class);
            int num = tree.getInteger("num");
            JSONArray array = tree.getJSONArray("members");
            size = array.size();
            total = num + size;
        }
        UserReq userReq = DataUtils.getUserReq(msg);

        if (StringUtils.isBlank(playerId) && StringUtils.isBlank(userReq.getUsername())){
            msg.setDesc("用户名或用户id不能为空");
            return msg;
        }

        PlayerResp player = null;
        if (StringUtils.isBlank(playerId)) {
            player = commonsService.getPlayerByUserName(msg);
        }

        Result<PlayerAccount> playerAccountResult = accountService.getPlayerAccount(playerId);
        PlayerAccount account = JsonUtil.parseJsonToObj(JsonUtil.parseObjToJson(playerAccountResult.getData()), PlayerAccount.class);
        Map data = new HashMap();
        data.put("total_income",new BigDecimal(0));
        data.put("total_property",new BigDecimal(0));
        data.put("total_usdt",account.getAccUsdt());
        data.put("total_mt",account.getAccMt());
        data.put("available_usdt",account.getAccUsdtAvailable());
        data.put("available_mt",account.getAccMtAvailable());
        data.put("frozen_usdt",account.getAccUsdtFreeze());
        data.put("frozen_mt",account.getAccMtFreeze());
        data.put("commerce_member",total);
        data.put("invite",player1.getPlayerInvite());

        MessageData resultData = new MessageData(msg.getData().getType(),msg.getData().getModel());
        resultData.setData(data);
        msg.setData(resultData);
        msg.setDesc(playerAccountResult.getMsg());
        return msg;
    }
}
