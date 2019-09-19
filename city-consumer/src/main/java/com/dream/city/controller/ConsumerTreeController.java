package com.dream.city.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.service.CityMessageService;
import com.dream.city.service.ConsumerPlayerService;
import com.dream.city.service.ConsumerPusherService;
import com.dream.city.service.ConsumerTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wvv
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerTreeController {
    @Autowired
    ConsumerPlayerService playerService;

    @Autowired
    ConsumerTreeService treeService;

    /**
     * 添加玩家商会关系
     * @param playerId
     * @param invite
     * @return
     */
    @RequestMapping("/tree/add")
    public Result jobPush(@RequestParam("playerId")String playerId,@RequestParam("invite")String invite){
        Result retPlayer = playerService.getPlayer(playerId);
        String jsonPlayer = JsonUtil.parseObjToJson(retPlayer.getData());
        JSONObject playerObj = JSON.parseObject(jsonPlayer);
        String playerInvite = playerObj.getString("playerInvite");

        Result retParent = playerService.getPlayerByInvite(invite);
        String jsonParent = JsonUtil.parseObjToJson(retPlayer.getData());
        JSONObject parentObj = JSON.parseObject(jsonParent);
        String parentId = parentObj.getString("playerId");


        treeService.addTree(parentId,playerId,playerInvite);
    }

}
