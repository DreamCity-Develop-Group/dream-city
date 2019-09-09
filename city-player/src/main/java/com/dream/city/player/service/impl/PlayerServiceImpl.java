package com.dream.city.player.service.impl;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.ResultCode;
import com.dream.city.base.utils.KeyGenerator;
import com.dream.city.player.domain.entity.Player;
import com.dream.city.player.domain.mapper.PlayerMapper;
import com.dream.city.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Wvv
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerMapper playerMapper;

    @Value("${spring.application.name}")
    private String appName;


    @Override
    public Message reg(Message message) {
        Message msg = new Message();
        String tip = "fail";
        if (message == null){
            message.setCode(ResultCode.fail.hashCode());
            message.setDesc(tip);
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
        boolean ret = save(playerSave);
        if (ret){
            tip=  "success";
            message.setCode(ResultCode.success.hashCode());
            message.setDesc(tip);
        }
        return msg;
    }

    @Override
    public boolean resetLoginPwd(String playerId, String userpass) {
        Player player = new Player();
        player.setPlayerId(playerId);
        player.setUserpass(userpass);
        int i = playerMapper.updateByPlayerId(player);
        return i > 0? Boolean.TRUE: Boolean.FALSE;
    }

    @Override
    public boolean resetTraderPwd(String playerId, String pwshop) {
        Player player = new Player();
        player.setPlayerId(playerId);
        player.setPwshop(pwshop);
        int i = playerMapper.updateByPlayerId(player);
        return i > 0? Boolean.TRUE: Boolean.FALSE;
    }

    @Override
    public boolean save(Player player) {
        player.setPlayerId(KeyGenerator.getUUID());
        player.setCreateDate(new Date());
        //加密  前端加密，后端不加密
        player.setUserpass(player.getUserpass());
        return playerMapper.insertPlayer(player)>0?Boolean.TRUE:Boolean.FALSE;
    }

    @Override
    public void delete(String playerId) {
        playerMapper.deleteByPlayerId(playerId);
    }

    @Override
    public Player update(Player player) {
        int i = playerMapper.updateByPlayerId(player);
        return i > 0? player: null;
    }

    @Override
    public List<Player> getPlayers(Player player) {
        List<Player> players = playerMapper.getPlayers(player);
        return players;
    }





}
