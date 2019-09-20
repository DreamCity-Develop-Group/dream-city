package com.dream.city.base.utils;


import com.alibaba.fastjson.JSON;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.entity.CityInvest;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class DataUtils {


    /**
     * 从入参Message中获取条件参数
     * @param msg
     * @return
     */
    public static Map<String,String> getCondition(Message msg){
        Map map = (Map)msg.getData().getT();
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



    public static CityInvest getInvestFromJsonReq(String jsonReq){
        Map map = JSON.parseObject(jsonReq,Map.class);
        CityInvest record = new CityInvest();
        /*record.setInEarning();
        record.setInEnd();
        record.setInLimit();
        record.setInName();
        record.setInStart();
        record.setInTax();*/

        return record;
    }



























}
