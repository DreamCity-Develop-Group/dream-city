package com.dream.city.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.req.UserReq;
import com.dream.city.base.model.resp.PlayerResp;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.service.ConsumerAccountService;
import com.dream.city.service.ConsumerCommonsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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


    /**
     * 获取玩家账户
     * @param msg
     * @return
     */
    @ApiOperation(value = "获取玩家账户", notes = "t入参username", response = Message.class)
    @RequestMapping("/getPlayerAccount")
    public Message getPlayerAccount(@RequestBody Message msg){
        logger.info("添加好友", JSONObject.toJSONString(msg));

        UserReq userReq = DataUtils.getUserReq(msg);
        String playerId = userReq.getPlayerId();
        PlayerResp player = null;
        if (StringUtils.isBlank(playerId)) {
            player = commonsService.getPlayerByNameOrNicke(msg);
            playerId = player.getPlayerId();
        }

        PlayerAccount record = new PlayerAccount();
        record.setAccPlayerId(playerId);
        Result<List<PlayerAccount>> playerAccountResult = accountService.getPlayerAccount(record);

        List<PlayerAccount> accountList = playerAccountResult.getData();
        List<Map> resultList = new ArrayList<>();
        Map<String,Object> map = null;
        for (PlayerAccount account : accountList){
            map = JSON.parseObject(JSON.toJSONString(account),Map.class);
            map.put("username",userReq.getUsername());
            map.put("invite",player.getPlayerInvite());
            map.put("commerce_lv",player.getGrade());
            map.put("commerce_member",player.getCommerceMember());
            resultList.add(map);
        }
        MessageData resultData = new MessageData(msg.getData().getType(),msg.getData().getModel());
        resultData.setData(resultList);
        msg.setData(resultData);
        return msg;
    }

}
