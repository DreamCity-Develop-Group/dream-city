package com.dream.city.player.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerExt;
import com.dream.city.base.model.entity.PlayerGrade;
import com.dream.city.base.utils.KeyGenerator;
import com.dream.city.base.model.mapper.PlayerGradeMapper;
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
    @Autowired
    private PlayerGradeMapper gradeMapper;


    @LcnTransaction
    @Transactional
    @Override
    public boolean createPlayer(Player player) throws BusinessException {
        String playerId = KeyGenerator.getUUID();

        player.setPlayerId(playerId);
        //密码前端加密
        boolean save = playerService.save(player);
        if (save){
            PlayerExt playerExt = new PlayerExt();
            playerExt.setPlayerId(playerId);
            Integer integer = playerExtService.insertPlayerExt(playerExt);
            save = (integer != null && integer > 0)? Boolean.TRUE: Boolean.FALSE;

            PlayerGrade grade = new PlayerGrade();
            grade.setPlayerId(playerId);
            grade.setGrade("L1");
            gradeMapper.insertSelective(grade);
        }
        return save;
    }
}
