package com.dream.city.player.controller;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.ResultCode;
import com.dream.city.player.domain.entity.Player;
import com.dream.city.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 玩家
 * @author Wvv
 */
@RestController
@RequestMapping("/player")
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
        String tip = "fail";
        if (message == null){
            message.setCode(ResultCode.fail.hashCode());
            return msg;
        }

        Map<String,String> player = (Map<String, String>) message.getData().getT();
        String playerName = player.get("username");
        String playerPass = player.get("userpass");
        String code = player.get("code");
        String nick = player.get("nick");
        String invite = player.get("invite");

        if(StringUtils.isEmpty(playerName)){
            tip=  "用户名或密码为空";
        }
        if(StringUtils.isEmpty(code)){
            tip = "短信验证码为空";
        }
        // 邀请码：为选填项，可填写可不填；
        /*if(StringUtils.isEmpty(invite)){
            tip = "邀请码为空";
        }*/
        if (StringUtils.isEmpty(nick)){
            nick = playerName;
        }

        Player playerSave = new Player();
        playerSave.setUsername(playerName);
        playerSave.setUserpass(playerPass);
        playerSave.setInvited(invite);
        playerSave.setNick(nick);
        boolean ret = playerService.save(playerSave);
        if (ret){
            tip=  "success";
            message.setCode(ResultCode.success.hashCode());
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
