package com.dream.city.service.routes.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;
import com.dream.city.service.consumer.ConsumerPlayerService;
import com.dream.city.service.consumer.ConsumerTreeService;
import com.dream.city.service.routes.TestLcnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;


@Service
@Transactional
public class TestLcnServiceImpl implements TestLcnService {

    @Autowired
    ConsumerTreeService treeService;

    @Autowired
    ConsumerPlayerService playerService;

    @LcnTransaction
    @Transactional
    @Override
    public Result setPlayerTree(String playerId, String invite, String pass) throws BusinessException {
        for (int i = 0; i < 100; i++) {
            Result result1 = treeService.addTree("8A2922A66F474A0DA9B10FB4BCD59BA0", playerId, invite);

        }
        Player player = playerService.getPlayerByPlayerId(playerId);


        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            int x = random.nextInt(999);

            int y = (x / 3);
            System.out.println("x:" + x + "y:" + y);
            if (y % 5 == 1) {
                int z = y / 0;
                System.out.println(z);
            }
            player.setPlayerTradePass(x+""+y);
            Result result2 = playerService.setTradePassword(player);
        }

        return Result.result(true);

    }
}
