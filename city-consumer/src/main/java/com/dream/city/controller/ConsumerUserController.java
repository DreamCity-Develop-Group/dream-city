package com.dream.city.controller;

import com.dream.city.service.ConsumerMessageService;
import com.dream.city.service.CityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wvv
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerUserController {

    @Autowired
    CityUserService cityUserService;

    @Autowired
    ConsumerMessageService messageService;

    /*@RequestMapping("/getcode")
    public Object getCode(){
        return messageService.getCode();
    }

    @RequestMapping("/get/user")
    public Object getUser(@RequestBody Message msg){
        Map<String,Object> data = (Map<String, Object>) msg.getData().getData();
        String id = data.get("id").toString();

        return cityUserService.users(id);
    }

    @RequestMapping
    public Object userCode(){
        Map<String,Object> map= new HashMap<>();
        String index = cityUserService.userIndex("index-user");
        Object code = messageService.getCode();
        map.put("index",index);
        map.put("code",code);
        return map;
    }

    *//**
     * 用户注册
     *//*
    @RequestMapping("/reg")
    public Message reg(@RequestBody Message message){
        System.out.println("**************************************");
        Message msg = new Message();
        String retUser = cityUserService.reg(message);
        String retMsg = messageService.validCode();
        if (retMsg.equals("success") && retUser.equals("success")){
            return msg;
        }
        return msg;
    }*/

}
