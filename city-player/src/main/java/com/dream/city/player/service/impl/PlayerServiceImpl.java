package com.dream.city.player.service.impl;

import com.dream.city.base.utils.KeyGenerator;
import com.dream.city.player.domain.entity.Player;
import com.dream.city.player.domain.mapper.PlayerMapper;
import com.dream.city.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

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
        return playerMapper.insertSelective(player)>0?Boolean.TRUE:Boolean.FALSE;
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
    public Player getPlayer(Player player) {
        List<Player> players = playerMapper.getPlayers(player);
        if (!CollectionUtils.isEmpty(players)) {
            return players.get(0);
        }
        return null;
    }

    @Override
    public Player getPlayerById(String playerId) {
        if (StringUtils.isEmpty(playerId)){
            return null;
        }
        return playerMapper.getPlayerById(playerId);
    }

    @Override
    public List<Player> getPlayers(Player player) {
        return playerMapper.getPlayers(player);
    }





}
