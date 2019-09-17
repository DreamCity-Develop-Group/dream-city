package com.dream.city.service.impl;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.req.PageReq;
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
    public Result getPlayers(PageReq pageReq) {
        return null;
    }

    @Override
    public Result reg(String jsonReq) {
        return null;
    }

    @Override
    public Result pwLogoin(String jsonReq) {
        return null;
    }

    @Override
    public Result codeLogoin(String jsonReq) {
        return null;
    }


    @Override
    public Result quit(String playerId) {
        return null;
    }

    @Override
    public Result forgetPwd(String username, String oldPwd) {
        return null;
    }

    @Override
    public Result resetLoginPwd(String playerId, String oldPwd, String newPwd) {
        return null;
    }

    @Override
    public Result resetTraderPwd(String playerId, String oldPwd, String newPwd) {
        return null;
    }

    @Override
    public Result getPlayerByName(String jsonReq) {
        return null;
    }


}
