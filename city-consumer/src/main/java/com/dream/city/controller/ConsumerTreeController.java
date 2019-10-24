package com.dream.city.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.RelationTree;
import com.dream.city.base.model.entity.SalesOrder;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.service.ConsumerAccountService;
import com.dream.city.service.ConsumerPlayerService;
import com.dream.city.service.ConsumerTreeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        } else {
            Player player = playerService.getPlayerByPlayerId(playerId);
            playerInvite = player.getPlayerInvite();
        }

        Result treeRet = treeService.getTree(playerId);
        if (treeRet.getSuccess() && treeRet.getData() != null) {
            Result allowed = treeService.getInvestAllowed(playerId);

            if (!allowed.getSuccess()) {
                message.getData().setCode(ReturnStatus.NEXT_OPT.getStatus());
                message.setDesc("尚未获得投资许可，设置交易密码");
                return message;
            }
        }

        //如果是系统的不使用玩家表判断
        String parentId = "";
        if (!"system".equals(invite)) {
            Map parent = (Map) playerService.getPlayerByInvite(invite).getData();
            parentId = parent.get("playerId").toString();
            if (parent == null) {
                message.getData().setCode(ReturnStatus.ERROR_INVITE.getStatus());
                message.setDesc(ReturnStatus.ERROR_INVITE.getDesc());
                return message;
            }
        } else {
            RelationTree tree = treeService.getTreeByRelation(invite);
            if (tree != null) {
                parentId = tree.getTreePlayerId();
            }
        }

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

        String username = jsonObject.getString("username");
        String tradePass = jsonObject.getString("tradePass");
        String playerId = jsonObject.getString("playerId");


        Result result = playerService.getPlayer(playerId);
        Player player = JsonUtil.parseJsonToObj(result.getData().toString(), Player.class);


        if (player != null && StringUtils.isBlank(player.getPlayerTradePass())) {
            msg.getData().setData(null);
            msg.getData().setCode(ReturnStatus.NOTSET_PASS.getStatus());
            msg.setDesc("加入经营许可失败，尚未设置交易密码");
            //没有设置密码
            return msg;
        }

        if (!tradePass.equals(player.getPlayerTradePass())) {
            msg.getData().setData(null);
            msg.getData().setCode(ReturnStatus.ERROR_PASS.getStatus());
            msg.setDesc("加入经营许可失败，交易密码不正确");
            return msg;
        }
        Result allowed = treeService.getInvestAllowed(playerId);
        if (allowed.getSuccess()) {
            msg.getData().setData(null);
            msg.getData().setCode(ReturnStatus.SUCCESS.getStatus());
            msg.setDesc("已经获得投资许可");
            return msg;
        }

        BigDecimal amount = new BigDecimal(10);

        if (amount.compareTo(new BigDecimal(0.00)) <= 0) {
            msg.getData().setData(null);
            msg.getData().setCode(ReturnStatus.INVALID.getStatus());
            msg.setDesc(ReturnStatus.INVALID.getDesc());
            return msg;
        }
        PlayerAccount account = accountService.getPlayerAccountByPlayerId(playerId);

        if (null != account && account.getAccUsdtAvailable().compareTo(amount) < 0) {
            msg.getData().setData(null);
            msg.getData().setCode(ReturnStatus.NOT_ENOUGH.getStatus());
            msg.setDesc("持有USDT不够支付许可费用");
            return msg;
        }

        //加入许可设置
        Result ret = treeService.joinInvestAllow(playerId, amount);
        if (ret.getSuccess()) {
            //设置推荐二维码生效，即用户可用状态
            Player player1 = playerService.getPlayerByPlayerId(playerId);
            player1.setIsValid("1");
            playerService.updatePlayer(player1);

            msg.getData().setCode(ReturnStatus.SUCCESS.getStatus());
            msg.setDesc("加入成功，可以投资运营");
            return msg;
        } else {
            msg.getData().setData(null);
            msg.getData().setCode(ReturnStatus.SET_FAILED.getStatus());
            msg.setDesc("加入失败，请重试");
            return msg;
        }
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
            LinkedHashMap ppid = (LinkedHashMap) playerService.getPlayerByAccount(username).getData();
            playerId = ppid.get("playerId").toString();
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
        Integer page = jsonObject.getInteger("page");
        String playerId = jsonObject.getString("playerId");
        if (StringUtils.isBlank(playerId)) {
            msg.getData().setCode(ReturnStatus.ERROR.getStatus());
            msg.setDesc("获取MT交易订单失败");
            return msg;
        }
        if (page == 0) {
            page = 1;
        }

        Result result = treeService.getOrderList(playerId, page);
        Map data = new HashMap();
        data.put("page", page);
        data.put("list", result.getData());
        msg.getData().setCode(result.getCode());
        msg.setDesc(result.getMsg());
        msg.getData().setData(data);
        return msg;
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
        String isAuto = jsonObject.getString("isAuto");
        boolean isOpen = false;
        if ("yes".equals(isAuto)) {
            isOpen = true;
            if (amount == null) {
                amount = new BigDecimal(100);
            }
            if (StringUtils.isNotBlank(playerId)) {
                playerId = jsonObject.getString("playerId");
            } else {
                Player player = (Player) playerService.getPlayerByAccount(username).getData();

                playerId = player.getPlayerId();
            }
            //@RequestParam("playerId")String playerId,@RequestParam("amount")BigDecimal amount
            Message message = new Message("server", "client", new MessageData("setAutoSend", "/consumer/tree"), "设置自动发货成功");
            PlayerAccount account = accountService.getPlayerAccountByPlayerId(playerId);
            BigDecimal no = new BigDecimal(0);
            if (account.getAccMtAvailable().compareTo(no) < 1 || account.getAccMtAvailable().compareTo(amount) < 1) {
                message.getData().setCode(ReturnStatus.NOT_ENOUGH_MT.getStatus());
                message.setDesc("备货额度不足，设置失败");
                return message;
            }
            Result result = treeService.setAutoSend(playerId);
            if (result.getSuccess()) {
                message.getData().setCode(ReturnStatus.SUCCESS.getStatus());
                return message;
            } else {
                message.getData().setCode(ReturnStatus.SET_FAILED.getStatus());
                message.setDesc("设置失败");
                return message;
            }
        } else {
            Message message = new Message("server", "client", new MessageData("setAutoSend", "/consumer/tree"), "关闭自动发货成功");
            Result result = treeService.setAutoSendClose(playerId);
            if (result.getSuccess()) {
                message.getData().setCode(ReturnStatus.SUCCESS.getStatus());
                return message;
            } else {
                message.getData().setCode(ReturnStatus.SET_FAILED.getStatus());
                message.setDesc("设置失败");
                return message;
            }
        }

    }

    /**
     * 订单发货
     *
     * @param msg
     * @return
     */
    @RequestMapping(value = "/tree/sendOrder", method = RequestMethod.POST)
    public Message sendOrder(@RequestBody Message msg) {
        Object dataMsg = msg.getData().getData();
        JSONObject jsonObject = JsonUtil.parseJsonToObj(JsonUtil.parseObjToJson(dataMsg), JSONObject.class);
        String username = jsonObject.getString("username");
        String playerId = jsonObject.getString("playerId");
        String[] orders = new String[]{};
        orders = jsonObject.getJSONArray("orders").toArray(orders);//.get("orders");
        String ordersId = "";
        /*for (String orderId : orders) {
            ordersId = ordersId.concat(orderId + "&");
        }*/

        for (int i =0;i<orders.length;i++){
            if(i == orders.length-1){
                ordersId+=orders[i];
            }
            ordersId+=orders[i]+"_";
        }
        Result result = treeService.sendOrder(playerId, ordersId);
        Message message = new Message("server", "client", new MessageData("sendOrder", "/consumer/tree"), "发货成功");
        if (result.getSuccess()) {
            message.getData().setCode(ReturnStatus.SUCCESS.getStatus());
            message.getData().setData(result.getData());
            return message;
        } else {
            message.getData().setCode(result.getCode());
            message.setDesc("发货失败:" + result.getMsg());
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

        Result result = treeService.createOrder(playerId, amount);

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

        Result result = treeService.checkOrderPass(playerId, confirmPass);

        msg.getData().setCode(result.getCode());
        msg.getData().setData(null);
        msg.setDesc(result.getMsg());
        return msg;
    }


}
