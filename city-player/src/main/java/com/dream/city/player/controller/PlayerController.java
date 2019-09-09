package com.dream.city.player.controller;

import com.dream.city.base.model.Message;
import com.dream.city.player.domain.entity.Player;
import com.dream.city.player.service.PlayerService;
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
public class PlayerController {

    @Autowired
    PlayerService playerService;

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

        Player u = new Player();
        u.setUsername(userName);
        u.setUserpass(userPass);
        u.setInvited(invite);
        u.setNick(nick);
        boolean ret = playerService.save(u);
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

        playerService.delete(str);
        return "Hello "+ str;
    }

    @RequestMapping("/get/{id}")
    public List getUser(@PathVariable(value = "id")String id){
        Player player = new Player();
        player.setPlayerId(id);
        List<Player> players = playerService.getPlayers(player);

        return players;
    }


}
