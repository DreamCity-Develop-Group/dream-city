package com.dream.city.controller;

import com.dream.city.domain.Message;
import com.dream.city.domain.entity.User;
import com.dream.city.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Wvv
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 用户注册
     * @param message(用户名，用户密码)
     * @return
     */

    @RequestMapping("/reg")
    public Message reg(@RequestBody Message message){
        Message msg = new Message();
        String tip = "";
        if (message == null){
            tip= "fail";
        }

        Map<String,String> user = (Map<String, String>) message.getData().getT();
        String userName = user.get("username");
        String userPass = user.get("userpass");
        String code = user.get("code");
        String nick = user.get("nick");
        String invite = user.get("invite");

        if(null==userName || null == userPass ){
            tip=  "用户名或密码为空";
        }
        if(null == code){
            tip = "短信验证码为空";
        }
        if(null == invite){
            tip = "邀请码为空";
        }
        if (null == nick){
            nick = userName;
        }
        User u = new User();
        u.setUId("0");
        u.setUName(userName);
        u.setUPass(userPass);
        u.setUInvite(invite);
        u.setUNick(nick);
        boolean ret = userService.saveUser(u);
        if (ret){
            tip=  "success";
        }
        return msg;

    }

    @RequestMapping("/index/{str}")
    public String index(@PathVariable(value = "str")String str){
        if(str == null){
            return "Hello str...";
        }

        userService.deleteUser(555);
        return "Hello "+ str;
    }

    @RequestMapping("/get/{id}")
    public List getUser(@PathVariable(value = "id")Integer id){
        List<User> users = new ArrayList<>();
        users = userService.getUsers(null);
        if(id != null){
            return users;
        }

        users.add(new User("1","Wvv1","123456","w1","2541s"));
        users.add(new User("2","Wvv2","123456","w2","2441s"));
        users.add(new User("3","Wvv3","123456","w3","2941s"));

        userService.deleteUser(555);
        return users;
    }


}
