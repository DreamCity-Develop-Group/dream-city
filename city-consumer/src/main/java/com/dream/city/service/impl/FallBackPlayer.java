package com.dream.city.service.impl;

import com.dream.city.domain.req.UserReq;
import com.dream.city.service.ConsumerPlayerService;

/**
 * @author Wvv
 */
public class FallBackPlayer implements ConsumerPlayerService {

    @Override
    public String getPlayer(String playerId) {
        return null;
    }

    @Override
    public String getPlayers(UserReq jsonReq) {
        return null;
    }

    @Override
    public String reg(UserReq jsonReq) {
        return null;
    }

    @Override
    public String login(UserReq jsonReq) {
        return null;
    }

    @Override
    public String quit(String playerId) {
        return null;
    }

    @Override
    public String resetLoginPwd(String playerId, String userpass) {
        return null;
    }

    @Override
    public String resetTraderPwd(String playerId, String pwshop) {
        return null;
    }
}
