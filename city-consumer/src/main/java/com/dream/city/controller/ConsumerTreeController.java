package com.dream.city.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.SalesOrder;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.service.ConsumerAccountService;
import com.dream.city.service.ConsumerPlayerService;
import com.dream.city.service.ConsumerTreeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
     *
     * @param msg
     * @return
     */
    @RequestMapping("/tree/add")
    public Message treeAdd(@RequestBody Message msg) {
        Object dataMsg = msg.getData().getData();
        JSONObject jsonObject = JsonUtil.parseJsonToObj(JsonUtil.parseObjToJson(dataMsg), JSONObject.class);
        String username = jsonObject.getString("username");
        String playerId = jsonObject.getString("playerId");
        String playerInvite = "";
        String invite = jsonObject.getString("invite");
        Message message = new Message(
                msg.getSource(),
                msg.getTarget(),
                new MessageData(
                        msg.getData().getType(),
                        msg.getData().getModel(),
                        null,
                        0
                ),
                String.valueOf(System.currentTimeMillis())
        );


        if (StringUtils.isBlank(playerId)) {
            Map player = (Map) playerService.getPlayerByAccount(username).getData();

            playerId = player.get("playerId").toString();
            playerInvite = player.get("playerInvite").toString();
        }else {
            String p = playerService.getPlayer(playerId).getData().toString();
            Player player = JsonUtil.parseJsonToObj(p,Player.class);
            playerInvite = player.getPlayerInvite();
        }

        Result treeRet = treeService.getTree(playerId);
        if (treeRet.getSuccess() && treeRet.getData()!=null){
            Result allowed = treeService.getInvestAllowed(playerId);

            if (!allowed.getSuccess()) {
                message.getData().setCode(ReturnStatus.NEXT_OPT.getStatus());
                message.setDesc("尚未获得投资许可，设置交易密码");
                return message;
            }
        }


        Map parent = (Map)  playerService.getPlayerByInvite(invite).getData();
        if (parent == null){
            message.getData().setCode(ReturnStatus.ERROR_INVITE.getStatus());
            message.setDesc(ReturnStatus.ERROR_INVITE.getDesc());
            return message;
        }
        String parentId = parent.get("playerId").toString();

        Result result = treeService.addTree(parentId, playerId, playerInvite);
        if (result.getSuccess()) {
            message.getData().setCode(ReturnStatus.SET_SUCCESS.getStatus());
            message.setDesc(ReturnStatus.SET_SUCCESS.getDesc());

            return message;
        }
        message.getData().setCode(ReturnStatus.SET_FAILED.getStatus());
        message.setDesc(ReturnStatus.SET_FAILED.getDesc());
        return message;
    }

    /**
     * 添加经营许可
     *
     * @param msg
     * @return
     */
    @RequestMapping("/tree/join")
    public Message Join(@RequestBody Message msg) {
        Object dataMsg = msg.getData().getData();
        JSONObject jsonObject = JsonUtil.parseJsonToObj(JsonUtil.parseObjToJson(dataMsg), JSONObject.class);
        //@RequestParam("playerId")String playerId, @RequestParam("amount")BigDecimal amount
        String username = jsonObject.getString("username");
        String playerId = "";
        String tradePass = jsonObject.getString("tradePass");
        if (StringUtils.isNotBlank(playerId)) {
            playerId = jsonObject.getString("playerId");
        } else {
            Map player = (Map) playerService.getPlayerByAccount(username).getData();

            playerId = player.get("playerId").toString();
        }


        Message message = new Message(
                "server", "client",
                new MessageData("join", "/consumer/tree",0),
                "加入经营许可失败，尚未设置交易 密码");
        Result result = playerService.getPlayer(playerId);
        Player player = JsonUtil.parseJsonToObj(result.getData().toString(), Player.class);
        Result allowed = treeService.getInvestAllowed(playerId);

        if (allowed.getSuccess()) {
            message.setDesc("已经获得投资许可");
            return message;
        }
        /*if (StringUtils.isBlank(player.getPlayerTradePass())) {
            //没有设置密码
            return message;
        }*/
        BigDecimal amount = new BigDecimal(10);

        if (amount.compareTo(new BigDecimal(0.00)) <= 0) {
            message.setDesc("设置的USDT不够");
            return message;
        }
        PlayerAccount account = accountService.getPlayerAccountByPlayerId(playerId);

        if (account.getAccUsdtAvailable().compareTo(amount) < 0) {
            message.setDesc("持有USDT不够支付许可费用");
            return message;
        }
        player.setPlayerTradePass(tradePass);
        //设置交易密码
        Result retPassSet = playerService.setTradePassword(player);
        if(retPassSet.getSuccess()){
            //加入许可设置
            Result ret = treeService.joinInvestAllow(playerId, amount);
            if (ret.getSuccess()) {
                //将分配时锁定的收益额度扣除
                account.setAccUsdt(account.getAccUsdt().subtract(amount));
                account.setAccUsdtFreeze(account.getAccUsdtFreeze().subtract(amount));

                Result updateAcc  = accountService.updatePlayerAccount(account);
                if (updateAcc.getSuccess()){
                    message.getData().setCode(ReturnStatus.SUCCESS.getStatus());
                    message.setDesc("加入成功，可以投资运营");
                }else {
                    Result updateAcc1  = accountService.updatePlayerAccount(account);
                    if (!updateAcc1.getSuccess()){
                        accountService.updatePlayerAccount(account);
                    }
                }

                return message;
            } else {
                message.getData().setCode(ReturnStatus.SET_FAILED.getStatus());
                message.setDesc("加入失败，请重试");
                return message;
            }
        }else {
            message.getData().setCode(ReturnStatus.SET_FAILED.getStatus());
            message.setDesc("设置交易密码失败");
        }

        return message;
    }

    /**
     * 获取商会成员
     *
     * @param msg
     * @return
     */
    @RequestMapping("/tree/getMembers")
    public Message getMembers(@RequestBody Message msg) {
        Object dataMsg = msg.getData().getData();
        JSONObject jsonObject = JsonUtil.parseJsonToObj(JsonUtil.parseObjToJson(dataMsg), JSONObject.class);
        String username = jsonObject.getString("username");
        String playerId = jsonObject.getString("playerId");

        if (StringUtils.isNotBlank(playerId)) {
            playerId = jsonObject.getString("playerId");
        } else {
            LinkedHashMap ppid = (LinkedHashMap)playerService.getPlayerByAccount(username).getData();
            playerId= ppid.get("playerId").toString();
            String json = JsonUtil.parseObjToJson(playerService.getPlayerByAccount(username).getData());


            //playerId = player.getPlayerId();
        }
        //@RequestParam("playerId")String playerId
        Integer level = 9;
        Result result = treeService.getMembers(playerId, level);

        msg.getData().setData(result.getData());


        return msg;
    }

    /**
     * 获取订单
     *
     * @param msg
     * @return
     */
    @RequestMapping("/tree/getSalesOrder")
    public Message getSalesOrder(@RequestBody Message msg) {
        Object dataMsg = msg.getData().getData();
        JSONObject jsonObject = JsonUtil.parseJsonToObj(JsonUtil.parseObjToJson(dataMsg), JSONObject.class);
        String username = jsonObject.getString("username");

        String playerId = "";
        if (StringUtils.isNotBlank(playerId)) {
            playerId = jsonObject.getString("playerId");
        } else {
            Player player = (Player) playerService.getPlayerByAccount(username).getData();

            playerId = player.getPlayerId();
        }
        //@RequestParam("playerId")String playerId
        Result result = treeService.getSalesOrder(playerId);
        Message message = new Message("server", "client", new MessageData("getSalesOrder", "/consumer/tree"), "获取MT交易订单");
        message.getData().setData(result.getData());

        return message;
    }

    /**
     * 设置自动发货，并备货
     *
     * @param msg
     * @return
     */
    @RequestMapping("/tree/setAutoSend")
    public Message setAutoSend(@RequestBody Message msg) {
        Object dataMsg = msg.getData().getData();
        JSONObject jsonObject = JsonUtil.parseJsonToObj(JsonUtil.parseObjToJson(dataMsg), JSONObject.class);
        String username = jsonObject.getString("username");
        String playerId = jsonObject.getString("playerId");
        BigDecimal amount = jsonObject.getBigDecimal("amount");
        if (StringUtils.isNotBlank(playerId)) {
            playerId = jsonObject.getString("playerId");
        } else {
            Player player = (Player) playerService.getPlayerByAccount(username).getData();

            playerId = player.getPlayerId();
        }
        //@RequestParam("playerId")String playerId,@RequestParam("amount")BigDecimal amount
        Message message = new Message("server", "client", new MessageData("setAutoSend", "/consumer/tree"), "设置自动发货成功");
        PlayerAccount account = accountService.getPlayerAccountByPlayerId(playerId);

        if (account.getAccMtAvailable().compareTo(amount) < 0) {
            message.setDesc("备货额度不足，设置失败");
            return message;
        }
        Result result = treeService.setAutoSend(playerId);
        if (result.getSuccess()) {
            return message;
        } else {
            message.setDesc("设置失败");
            return message;
        }
    }

    /**
     * 订单发货
     *
     * @param msg
     * @return
     */
    @RequestMapping("/tree/sendOrder")
    public Message sendOrder(@RequestBody Message msg) {
        Object dataMsg = msg.getData().getData();
        JSONObject jsonObject = JsonUtil.parseJsonToObj(JsonUtil.parseObjToJson(dataMsg), JSONObject.class);
        //String username = jsonObject.getString("username");
        String playerId = jsonObject.getString("playerId");

        List<String> orders = (List<String>) jsonObject.getJSONArray("orders").toJavaList(String.class);//.get("orders");

        //@RequestParam("playerId")String playerId,@RequestParam("orders") List<String> orders
        Result result = treeService.sendOrder(playerId, orders);
        Message message = new Message("server", "client", new MessageData("sendOrder", "/consumer/tree"), "发货成功");
        if (result.getSuccess()) {
            message.getData().setData(result.getData());
            return message;
        } else {
            message.setDesc("发货失败");
            return message;
        }
    }


    /**
     * 订单创建
     *
     * @param msg
     * @return
     */
    @RequestMapping("/tree/createOrder")
    public Message createOrder(@RequestBody Message msg) {
        Object dataMsg = msg.getData().getData();
        JSONObject jsonObject = JsonUtil.parseJsonToObj(JsonUtil.parseObjToJson(dataMsg), JSONObject.class);
        String username = jsonObject.getString("username");
        String playerId = jsonObject.getString("playerId");
        BigDecimal amount = jsonObject.getBigDecimal("amount");

        Result result =  treeService.createOrder(playerId,amount);

        msg.getData().setCode(result.getCode());
        msg.getData().setData(result.getData());
        return msg;
    }


    /**
     * 验证订单支付密码
     *
     * @param msg
     * @return
     */
    @RequestMapping("/tree/check/orderPass")
    public Message checkOrderPass(@RequestBody Message msg) {
        Object dataMsg = msg.getData().getData();
        JSONObject jsonObject = JsonUtil.parseJsonToObj(JsonUtil.parseObjToJson(dataMsg), JSONObject.class);
        String playerId = jsonObject.getString("playerId");
        String confirmPass = jsonObject.getString("confirmPass");

        Result result =  treeService.checkOrderPass(playerId,confirmPass);

        msg.getData().setCode(result.getCode());
        msg.getData().setData(null);
        return msg;
    }

    /**
     * 订单列表
     *
     * @param msg
     * @return
     */
    @RequestMapping("/tree/get/orderList")
    public Message getOrderList(@RequestBody Message msg) {
        Object dataMsg = msg.getData().getData();
        JSONObject jsonObject = JsonUtil.parseJsonToObj(JsonUtil.parseObjToJson(dataMsg), JSONObject.class);
        String playerId = jsonObject.getString("playerId");


        Result result =  treeService.getOrderList(playerId);

        msg.getData().setCode(result.getCode());
        msg.getData().setData(null);
        return msg;
    }



}
