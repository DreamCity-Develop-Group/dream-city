package com.dream.city.player.service.impl;

import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerExt;
import com.dream.city.base.utils.KeyGenerator;
import com.dream.city.player.service.PlayerExtService;
import com.dream.city.player.service.PlayerHandleService;
import com.dream.city.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PlayerHandleServiceImpl implements PlayerHandleService {

    @Autowired
    PlayerService playerService;
    @Autowired
    PlayerExtService playerExtService;



    @Override
    @Transactional
    public boolean createPlayer(Player player) {
        String playerId = KeyGenerator.getUUID();

        player.setPlayerId(playerId);
        //密码前端加密
        boolean save = playerService.save(player);
        if (save){
            PlayerExt playerExt = new PlayerExt();
            playerExt.setPlayerId(playerId);
            Integer integer = playerExtService.insertPlayerExt(playerExt);
            save = (integer != null && integer > 0)? Boolean.TRUE: Boolean.FALSE;
        }
        return save;
    }
}
