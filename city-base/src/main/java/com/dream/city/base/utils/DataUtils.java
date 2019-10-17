package com.dream.city.base.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.entity.CityFile;
import com.dream.city.base.model.entity.PlayerEarning;
import com.dream.city.base.model.req.*;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataUtils {



    public static String getTradeDetailType(String code){
        String desc = "";
        if ("RECHARGE".equalsIgnoreCase(code)){
            desc = "充值";
        }else if ("USDT_INVEST_VERIFY".equalsIgnoreCase(code)){
            desc = "投资冻结";
        }else if ("USDT_INVEST_FREEZE".equalsIgnoreCase(code)){
            desc = "投资审核通过扣款";
        }else if ("USDT_INVEST_UNFREEZE".equalsIgnoreCase(code)){
            desc = "投资解冻";
        }else if ("USDT_EARNINGS".equalsIgnoreCase(code)){
            desc = "投资收益";
        }else if ("MT_INVES_FREEZE".equalsIgnoreCase(code)){
            desc = "投资冻结税金";
        }else if ("MT_INVEST_PERSONAL_TAX".equalsIgnoreCase(code)){
            desc = "投资个人税金";
        }else if ("MT_INVEST_ENTERPRISE_TAX".equalsIgnoreCase(code)){
            desc = "投资企业税金";
        }else if ("MT_INVEST_QUOTA_TAX".equalsIgnoreCase(code)){
            desc = "定额税税金";
        }else if ("MT_INVEST_PERSONAL_TAX_UNFREEZE".equalsIgnoreCase(code)){
            desc = "投资个人税金解冻";
        }else if ("MT_INVEST_ENTERPRISE_TAX_UNFREEZE".equalsIgnoreCase(code)){
            desc = "投资企业税金解冻";
        }else if ("BUY_MT_FREEZE".equalsIgnoreCase(code)){
            desc = "购买mt冻结";
        }else if ("TRANSFER_FREEZE".equalsIgnoreCase(code)){
            desc = "转账冻结";
        }else if ("TRANSFER_VERIFY".equalsIgnoreCase(code)){
            desc = "转账审核通过扣款";
        }else if ("TRANSFER_TAX".equalsIgnoreCase(code)){
            desc = "转账所得税";
        }else if ("TRANSFER_UNFREEZE_USDT".equalsIgnoreCase(code)){
            desc = "转账审核不通过解冻";
        }else if ("TRANSFER_UNFREEZE_MT".equalsIgnoreCase(code)){
            desc = "转账审核不通过解冻税金";
        }else if ("WITHDRAW_FREEZE".equalsIgnoreCase(code)){
            desc = "提现冻结";
        }else if ("WITHDRAW_VERIFY".equalsIgnoreCase(code)){
            desc = "提现审核通过扣款";
        }else if ("WITHDRAW_TAX".equalsIgnoreCase(code)){
            desc = "提现扣税";
        }else if ("WITHDRAW_UNFREEZE_USDT".equalsIgnoreCase(code)){
            desc = "提现审核不通过解冻";
        }else if ("WITHDRAW_UNFREEZE_MT".equalsIgnoreCase(code)){
            desc = "提现审核不通过解冻税金";
        }else {
            desc = code;
        }
        return desc;
    }


    public static String getTradeStatus(String code){
        String desc = "";
        if ("FREEZE".equalsIgnoreCase(code)){
            desc = "冻结";
        }else if ("UNFREEZE".equalsIgnoreCase(code)){
            desc = "解冻";
        }else if ("OUT".equalsIgnoreCase(code)){
            desc = "已出账";
        }else if ("IN".equalsIgnoreCase(code)){
            desc = "已入账";
        }else {
            desc = code;
        }
        return desc;
    }


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
            userReq.setInvite(map.containsKey("invite") ? (String) map.get("invite") : null);
            userReq.setNick(map.containsKey("nick") ? (String) map.get("nick") : null);
            userReq.setPlayerId(map.containsKey("playerId") ? (String) map.get("playerId") : null);
            userReq.setOldpwshop(map.containsKey("oldpwshop") ? (String) map.get("oldpwshop") : null);
            userReq.setNewpwshop(map.containsKey("newpwshop") ? (String) map.get("newpwshop") : null);
            userReq.setUsername(map.containsKey("username") ? (String) map.get("username") : null);
            userReq.setUserpass(map.containsKey("userpass") ? (String) map.get("userpass") : null);
            userReq.setCode(map.containsKey("code") ? (String) map.get("code") : null);
            userReq.setOldpw(map.containsKey("oldpw") ? (String) map.get("oldpw") : null);
            userReq.setNewpw(map.containsKey("newpw") ? (String) map.get("newpw") : null);
            userReq.setImgUrl(map.containsKey("imgUrl") ? (String) map.get("imgUrl") : null);
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
        //earning.setEarnTax(earnTax);
        earning.setEarnPersonalTax(earnTax);
        return earning;
    }

    public static CityInvestReq getInvestFromMessage(Message msg){
        Map map = (Map)msg.getData().getData();
        Integer inId = map.containsKey("inId")?Integer.parseInt(String.valueOf(map.get("inId"))):null;
        String inName = map.containsKey("inName")?String.valueOf(map.get("inName")):null;
        String isValid = map.containsKey("isValid")?String.valueOf(map.get("isValid")):null;
        String playerId = map.containsKey("playerId")?String.valueOf(map.get("playerId")):null;
        String username = map.containsKey("username")?String.valueOf(map.get("username")):null;

        CityInvestReq result = new CityInvestReq();
        result.setInId(inId);
        result.setInName(inName);
        result.setIsValid(isValid);
        result.setPlayerId(playerId);
        result.setPlayerName(username);
        return result;
    }

    public static InvestOrderReq getInvestOrderReqFromMessage(Message msg){
        Map map = (Map)msg.getData().getData();
        Integer orderId = map.containsKey("orderId")?Integer.parseInt(String.valueOf(map.get("orderId"))):null;
        Integer investId = map.containsKey("investId")?Integer.parseInt(String.valueOf(map.get("investId"))):null;
        BigDecimal orderAmount = map.containsKey("orderAmount")?BigDecimal.valueOf(Double.valueOf(String.valueOf(map.get("orderAmount")))):BigDecimal.ZERO;
        String amountType = map.containsKey("amountType")?String.valueOf(map.get("amountType")):null;
        String inName = map.containsKey("inName")?String.valueOf(map.get("inName")):null;
        String playerId = map.containsKey("playerId")?String.valueOf(map.get("playerId")):null;
        String payerName = map.containsKey("username")?String.valueOf(map.get("username")):null;
        String orderState = map.containsKey("orderState")?String.valueOf(map.get("orderState")):null;
        Integer orderRepeat = map.containsKey("orderRepeat")?Integer.parseInt(String.valueOf(map.get("orderRepeat"))):0;

        InvestOrderReq result = new InvestOrderReq();
        result.setInName(inName);
        result.setInvestId(investId);
        result.setOrderId(orderId);
        result.setOrderAmount(orderAmount);
        result.setAmountType(amountType);
        result.setPlayerId(playerId);
        result.setPayerName(payerName);
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
        result.setAccAddr(accAddr);
        result.setTradeType(tradeType);
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
        likes.setLikedGetTotal(likedInvestTotal);
        likes.setLikePlayerId(likePlayerId);
        return likes;
    }

    public static CityFile getCityFileFromMsg(Message msg){
        Map map = (Map)msg.getData().getData();
        String id = map.containsKey("id")?(String)map.get("id"):"0";
        String fileName = map.containsKey("fileName")?(String)map.get("fileName"):null;
        String fileType = map.containsKey("fileType")?(String)map.get("fileType"):null;

        CityFile data = new CityFile();
        data.setId(Long.parseLong(id));
        data.setFileName(fileName);
        data.setFileType(fileType);
        return data;
    }


    public static FriendsReq getFriendsReq(Message msg){
        Map map = (Map)msg.getData().getData();
        String username = map.containsKey("username")?(String) map.get("username"):null;
        if (StringUtils.isBlank(username)){
            username = map.containsKey("playerName")?(String) map.get("playerName"):null;
        }
        String nick = map.containsKey("nick")?(String) map.get("nick"):null;

        FriendsReq resultMap = new FriendsReq();
        resultMap.setPlayerName(username);
        resultMap.setFriendNick(nick);
        return resultMap;
    }


    /**
     * 转化为List<T>
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> getDataArray(Object data, Class<T> clazz) {
        String jsonString = JSONObject.toJSONString(data);
        return JSONObject.parseArray(jsonString, clazz);
    }

    /**
     * 转化为T
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getData(Object data, Class<T> clazz) {
        Object json = JSON.toJSON(data);
        String jsonString = JSONObject.toJSONString(json);
        return JSONObject.parseObject(jsonString, clazz);
    }


    public static <T> T toJavaObject(Object data, Class<T> clazz) {
        Object json = JSON.toJSON(data);
        String jsonString = JSON.toJSONStringWithDateFormat(json,DateUtils.DATE_FORMAT_DEFAULT);
        return JSON.toJavaObject(JSON.parseObject(jsonString),clazz);
    }















}
