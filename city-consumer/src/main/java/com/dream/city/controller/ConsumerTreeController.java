package com.dream.city.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.service.ConsumerAccountService;
import com.dream.city.service.ConsumerPlayerService;
import com.dream.city.service.ConsumerTreeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

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

    @Autowired
    private ConsumerAccountService accountService;


    /**
     * 添加玩家商会关系
     * @param playerId
     * @param invite
     * @return
     */
    @RequestMapping("/tree/add")
    public Message jobPush(@RequestParam("playerId")String playerId,@RequestParam("invite")String invite){
        MessageData data = new MessageData("add","/consumer/tree");
        Message message = new Message("server","client",data,"加入失败");
        Result retPlayer = playerService.getPlayer(playerId);
        String jsonPlayer = JsonUtil.parseObjToJson(retPlayer.getData());
        JSONObject playerObj = JSON.parseObject(jsonPlayer);
        String playerInvite = playerObj.getString("playerInvite");

        Result retParent = playerService.getPlayerByInvite(invite);
        String jsonParent = JsonUtil.parseObjToJson(retPlayer.getData());
        JSONObject parentObj = JSON.parseObject(jsonParent);
        String parentId = parentObj.getString("playerId");


        Result result = treeService.addTree(parentId,playerId,playerInvite);
        if (result.getSuccess()){
            message.setDesc("加入成功");
            data.setData(result.getData());
            message.setData(data);
            return message;
        }
        return message;
    }

    /**
     * 添加经营许可
     *
     * @param playerId
     * @param amount
     * @return
     */
    @RequestMapping("/tree/join")
    public Message Join(@RequestParam("playerId")String playerId, @RequestParam("amount")BigDecimal amount){
        Message message = new Message("server","client",new MessageData("join","/consumer/tree"),"加入经营许可失败，尚未设置交易 密码");
        Result result = playerService.getPlayer(playerId);
        Player player = JsonUtil.parseJsonToObj(result.getData().toString(),Player.class);
        Result allowed = treeService.getInvestAllowed(playerId);
        if (allowed.getSuccess()){
            message.setDesc("已经获得投资许可");
            return message;
        }
        if (StringUtils.isBlank(player.getPlayerTradePass())){
            //没有设置密码
            return message;
        }

        if (amount.compareTo(new BigDecimal(0.00))<=0){
            message.setDesc("设置的MT不够");
            return message;
        }
        PlayerAccount account = accountService.getPlayerAccountByPlayerId(playerId);
        if (account.getAccMtAvailable().compareTo(amount)<0){
            message.setDesc("持有MT不够支付许可费用");
            return message;
        }

        Result ret = treeService.joinInvestAllow(playerId,amount);
        if (ret.getSuccess()){

            message.setDesc("加入成功，可以投资运营");
            return message;
        }else {
            message.setDesc("加入失败，请重试");
            return message;
        }

    }

    /**
     * 获取商会成员
     *
     * @param playerId
     * @return
     */
    @RequestMapping("/tree/getMembers")
    public Message getMembers(@RequestParam("playerId")String playerId){
        Integer level = 1;
        Result result = treeService.getMembers(playerId, level);

        Message message = new Message("server","client",new MessageData("getMembers","/consumer/tree"),"获取商会成员");

        message.getData().setData(result.getData());
        return message;
    }

    @RequestMapping("/tree/getSalesOrder")
    public Message getSalesOrder(@RequestParam("playerId")String playerId){
        Result result = treeService.getSalesOrder(playerId);
        Message message = new Message("server","client",new MessageData("getSalesOrder","/consumer/tree"),"获取MT交易订单");
        message.getData().setData(result.getData());

        return message;
    }

    /**
     * 设置自动发货，并备货
     *
     * @param playerId
     * @return
     */
    @RequestMapping("/tree/setAutoSend")
    public Message setAutoSend(@RequestParam("playerId")String playerId,@RequestParam("amount")BigDecimal amount){
        Message message = new Message("server","client",new MessageData("setAutoSend","/consumer/tree"),"设置自动发货成功");
        PlayerAccount account = accountService.getPlayerAccountByPlayerId(playerId);

        if (account.getAccMt().compareTo(amount)<0){
            message.setDesc("备货额度不足，设置失败");
            return message;
        }
        Result result = treeService.setAutoSend(playerId);
        if (result.getSuccess()){
            return message;
        }else {
            message.setDesc("设置失败");
            return message;
        }
    }

    /**
     * 订单发货
     *
     * @param playerId
     * @param orders
     * @return
     */
    @RequestMapping("/tree/sendOrder")
    public Message sendOrder(@RequestParam("playerId")String playerId,@RequestParam("orders") List<String> orders){
        Result result = treeService.sendOrder(playerId,orders);
        Message message = new Message("server","client",new MessageData("sendOrder","/consumer/tree"),"发货成功");
        if (result.getSuccess()){
            message.getData().setData(result.getData());
            return message;
        }else{
            message.setDesc("发货失败");
            return message;
        }
    }

}
