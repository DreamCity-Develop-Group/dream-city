package com.dream.city.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.req.UserReq;
import com.dream.city.base.model.resp.PlayerResp;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.service.ConsumerAccountService;
import com.dream.city.service.ConsumerCommonsService;
import com.dream.city.service.ConsumerPlayerService;
import com.dream.city.service.ConsumerTreeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "获取玩家账户", description = "获取玩家账户信息")
@RestController
@RequestMapping("/consumer")
public class ConsumerAccountController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private ConsumerCommonsService commonsService;
    @Autowired
    private ConsumerAccountService accountService;
    @Autowired
    ConsumerPlayerService playerService;
    @Autowired
    ConsumerTreeService treeService;


    /**
     * 获取玩家账户
     * @param msg
     * @return
     */
    @ApiOperation(value = "获取玩家账户", httpMethod = "POST", notes = "t入参username", response = Message.class)
    @RequestMapping("/account/getPlayerAccount")
    public Message getPlayerAccount(@RequestBody Message msg){
        logger.info("添加好友", JSONObject.toJSONString(msg));
        Object dataMsg = msg.getData().getData();
        JSONObject jsonObject = JsonUtil.parseJsonToObj(JsonUtil.parseObjToJson(dataMsg), JSONObject.class);
        String username = jsonObject.getString("username");
        String token = jsonObject.getString("token");
        String playerId = jsonObject.getString("playerId");

        Player player1 = playerService.getPlayerByPlayerId(playerId);
        Result trees = treeService.getMembers(playerId,9);
        JSONObject tree = JsonUtil.parseJsonToObj(JsonUtil.parseObjToJson(trees.getData()), JSONObject.class);
        int num = tree.getInteger("num");
        JSONArray array = tree.getJSONArray("members");
        int size = array.size();
        int total = num+size;
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
