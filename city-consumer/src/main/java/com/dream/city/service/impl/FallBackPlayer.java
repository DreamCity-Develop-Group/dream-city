package com.dream.city.service.impl;

import com.dream.city.base.model.Result;
import com.dream.city.service.ConsumerPlayerService;

/**
 * @author Wvv
 */
public class FallBackPlayer implements ConsumerPlayerService {


    @Override
    public Result getPlayer(String playerId) {
        return null;
    }

    @Override
    public Result getPlayers(String jsonReq) {
        return null;
    }

    @Override
    public Result reg(String jsonReq) {
        return null;
    }

    @Override
    public Result login(String jsonReq) {
        return null;
    }

    @Override
    public Result quit(String playerId) {
        return null;
    }

    @Override
    public Result resetLoginPwd(String playerId, String userpass) {
        return null;
    }

    @Override
    public Result resetTraderPwd(String playerId, String pwshop) {
        return null;
    }
}
