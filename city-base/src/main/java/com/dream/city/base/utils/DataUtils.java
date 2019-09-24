package com.dream.city.base.utils;


import com.alibaba.fastjson.JSON;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.model.entity.PlayerEarning;
import com.dream.city.base.model.req.InvestOrderReq;
import com.dream.city.base.model.req.PlayerAccountReq;
import com.dream.city.base.model.req.PlayerLikesReq;
import com.dream.city.base.model.req.UserReq;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class DataUtils {


    /**
     * 从入参Message中获取条件参数
     * @param msg
     * @return
     */
    public static Map<String,String> getCondition(Message msg){
        Map map = (Map)msg.getData().getData();
        String username = map.containsKey("username")?(String) map.get("username"):null;
        /*String playerId = map.containsKey("playerId")?(String) map.get("playerId"):null;
        String nick = map.containsKey("nick")?(String) map.get("nick"):null;
        String token = map.containsKey("token")?(String) map.get("token"):null;
        String userpass = map.containsKey("userpass")?(String) map.get("userpass"):null;
        String pwshop = map.containsKey("pwshop")?(String) map.get("pwshop"):null;
        String oldpw = map.containsKey("oldpw")?(String) map.get("oldpw"):null;
        String newpw = map.containsKey("newpw")?(String) map.get("newpw"):null;
        String invite = map.containsKey("invite")?(String) map.get("invite"):null;
        String code = map.containsKey("code")?(String) map.get("code"):null;*/

        if (StringUtils.isBlank(username)){
            username = map.containsKey("playerName")?(String) map.get("playerName"):null;
        }

        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("username",username);
        resultMap.putAll(map);
        return resultMap;
    }


    public static UserReq getUserReq(Message message) {
        Map map = (Map) message.getData().getData();
        UserReq userReq = new UserReq();
        if (map != null) {
            userReq.setInvite(map.containsKey("invited") ? (String) map.get("invited") : null);
            userReq.setNick(map.containsKey("nick") ? (String) map.get("nick") : null);
            userReq.setPlayerId(map.containsKey("playerId") ? (String) map.get("playerId") : null);
            userReq.setPwshop(map.containsKey("pwshop") ? (String) map.get("pwshop") : null);
            userReq.setUsername(map.containsKey("username") ? (String) map.get("username") : null);
            userReq.setUserpass(map.containsKey("userpass") ? (String) map.get("userpass") : null);
            userReq.setCode(map.containsKey("code") ? (String) map.get("code") : null);
            userReq.setOldpw(map.containsKey("oldpw") ? (String) map.get("oldpw") : null);
            userReq.setNewpw(map.containsKey("newpw") ? (String) map.get("newpw") : null);
        }
        return userReq;
    }


    public static PlayerEarning getEarningFromJsonReq(Message msg){
        Map map = (Map)msg.getData().getData();
        Integer earnId = map.containsKey("earnId")?Integer.parseInt(String.valueOf(map.get("earnId"))):null;
        String earnPlayerId = map.containsKey("earnPlayerId")?String.valueOf(map.get("earnPlayerId")):null;
        BigDecimal earnMax = map.containsKey("earnMax")? (BigDecimal) map.get("earnMax") :null;
        BigDecimal earnTax = map.containsKey("earnTax")?(BigDecimal) map.get("earnTax"):null;

        PlayerEarning earning = new PlayerEarning();
        earning.setEarnId(earnId);
        earning.setEarnMax(earnMax);
        earning.setEarnPlayerId(earnPlayerId);
        earning.setEarnTax(earnTax);
        return earning;
    }

    public static CityInvest getInvestFromMessage(Message msg){
        Map map = (Map)msg.getData().getData();
        Integer investId = map.containsKey("investId")?Integer.parseInt(String.valueOf(map.get("investId"))):null;
        String inName = map.containsKey("inName")?String.valueOf(map.get("inName")):null;

        CityInvest result = new CityInvest();
        result.setInId(investId);
        result.setInName(inName);
        return result;
    }

    public static InvestOrderReq getInvestOrderReqFromMessage(Message msg){
        Map map = (Map)msg.getData().getData();
        Integer orderId = map.containsKey("orderId")?Integer.parseInt(String.valueOf(map.get("orderId"))):null;
        Integer investId = map.containsKey("investId")?Integer.parseInt(String.valueOf(map.get("investId"))):null;
        BigDecimal orderAmount = map.containsKey("orderAmount")?BigDecimal.valueOf(Double.valueOf(String.valueOf(map.get("orderAmount")))):BigDecimal.ZERO;
        String amountType = map.containsKey("amountType")?String.valueOf(map.get("amountType")):null;
        String inName = map.containsKey("inName")?String.valueOf(map.get("inName")):null;
        String payerId = map.containsKey("payerId")?String.valueOf(map.get("payerId")):null;
        String orderState = map.containsKey("orderState")?String.valueOf(map.get("orderState")):null;
        Integer orderRepeat = map.containsKey("orderRepeat")?Integer.parseInt(String.valueOf(map.get("orderRepeat"))):0;

        InvestOrderReq result = new InvestOrderReq();
        result.setInName(inName);
        result.setInvestId(investId);
        result.setOrderId(orderId);
        result.setOrderAmount(orderAmount);
        result.setAmountType(amountType);
        result.setPayerId(payerId);
        result.setOrderRepeat(orderRepeat);
        result.setOrderState(orderState);
        return result;
    }


    public static PlayerAccountReq getPlayerAccountReqFromMessage(Message msg){
        Map map = (Map)msg.getData().getData();
        Integer accId = map.containsKey("accId")?Integer.parseInt(String.valueOf(map.get("accId"))):null;
        String accPlayerId = map.containsKey("playerId")?String.valueOf(map.get("playerId")):null;
        String userName = map.containsKey("username")?String.valueOf(map.get("username")):null;
        String nick = map.containsKey("nick")?String.valueOf(map.get("nick")):null;
        String accAddr = map.containsKey("accAddr")?String.valueOf(map.get("accAddr")):null;
        String tradeType = map.containsKey("tradeType")?String.valueOf(map.get("tradeType")):null;
        String accPass = map.containsKey("accPass")?String.valueOf(map.get("accPass")):null;
        BigDecimal accUsdt = map.containsKey("accUsdt")?BigDecimal.valueOf(Double.parseDouble(String.valueOf(map.get("accUsdt")))):null;
        BigDecimal accMt = map.containsKey("accMt")?BigDecimal.valueOf(Double.parseDouble(String.valueOf(map.get("accMt")))):null;

        PlayerAccountReq result = new PlayerAccountReq();
        result.setAccId(accId);
        result.setAccPass(accPass);
        result.setAccPlayerId(accPlayerId);
        result.setUsername(userName);
        result.setNick(nick);
        result.setAccMt(accMt);
        result.setAccUsdt(accUsdt);
        return result;
    }




    public static PlayerLikesReq getPlayerLikes(String jsonReq){
        Map map = JSON.parseObject(jsonReq,Map.class);
        String likedIdStr = map.containsKey("likedId")?(String)map.get("likedId"):null;
        String likedInvestIdStr = map.containsKey("likedInvestId")?(String)map.get("likedInvestId"):null;
        Integer likedInvestTotal = map.containsKey("likedInvestTotal")?(Integer)map.get("likedInvestTotal"):1;
        String likedPlayerId = map.containsKey("likedPlayerId")?(String)map.get("likedPlayerId"):null;
        String likePlayerId = map.containsKey("likePlayerId")?(String)map.get("likePlayerId"):null;

        Integer likedId = likedIdStr == null? null: Integer.parseInt(likedIdStr);
        Integer likedInvestId = likedInvestIdStr == null? null: Integer.parseInt(likedInvestIdStr);

        PlayerLikesReq likes = new PlayerLikesReq();
        likes.setLikedId(likedId);
        likes.setLikedPlayerId(likedPlayerId);
        likes.setLikedInvestId(likedInvestId);
        likes.setLikedInvestTotal(likedInvestTotal);
        likes.setLikePlayerId(likePlayerId);
        return likes;
    }




















}
