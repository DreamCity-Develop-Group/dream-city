package com.dream.city.player.service.impl;

import com.dream.city.base.utils.KeyGenerator;
import com.dream.city.player.domain.entity.Player;
import com.dream.city.player.domain.mapper.PlayerMapper;
import com.dream.city.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        playerMapper.updateByPlayerId(player);
        return player;
    }

    @Override
    public List<Player> getPlayers(Player player) {
        List<Player> players = playerMapper.getPlayers(player);
        return players;
    }


    /*private Player getPlayer(User user){
        Player record = new Player();
        record.setPlayerId(UUID.randomUUID().toString().replace("-",""));
        record.setCreateDate(new Date());
        record.setInvited(user.getUInvite());
        record.setNick(user.getUNick());
        record.setUsername(user.getUName());
        record.setUserpass(user.getUPass());//todo  加密

        return record;
    }

    private User getUser(Player player){
        User record = new User();
        record.setUId(player.getPlayerId());
        record.setUInvite(player.getInvited());
        record.setUName(player.getUsername());
        record.setUNick(player.getNick());
        //record.setUPass();

        return record;
    }*/



}
