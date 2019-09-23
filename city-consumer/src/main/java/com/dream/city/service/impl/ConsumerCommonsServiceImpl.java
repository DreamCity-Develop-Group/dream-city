package com.dream.city.service.impl;

import com.alibaba.fastjson.JSON;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.req.PlayerAccountReq;
import com.dream.city.base.model.resp.PlayerResp;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.service.ConsumerCommonsService;
import com.dream.city.service.ConsumerPlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConsumerCommonsServiceImpl implements ConsumerCommonsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConsumerPlayerService playerService;

    @Override
    public PlayerResp getPlayerByNameOrNicke(Message msg) {
        PlayerAccountReq accountReq = DataUtils.getPlayerAccountReqFromMessage(msg);
        String playerName = accountReq.getUsername();
        String playerNick = accountReq.getNick();
        Map map = new HashMap<>();
        map.put("playerName",playerName);
        map.put("playerNick",playerNick);

        Result<String> result = playerService.getPlayerByName(JSON.toJSONString(map));
        if (result.getSuccess()){
            PlayerResp player = JSON.toJavaObject(JSON.parseObject(result.getData()),PlayerResp.class);
            return player;
        }
        return null;
    }
}
