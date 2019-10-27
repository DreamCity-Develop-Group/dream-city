package com.dream.city.service.consumer.impl;

import com.dream.city.base.model.Page;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerExt;
import com.dream.city.base.model.req.PlayerReq;
import com.dream.city.service.consumer.ConsumerPlayerService;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Wvv
 */
public class FallBackPlayer implements ConsumerPlayerService {


    @Override
    public Result getPlayer(String playerId) {
        return null;
    }

    @Override
    public Result<Boolean> updatePlayerHeadImg(PlayerExt record) {
        return null;
    }

    @Override
    public Result getPlayers(Page pageReq) {
        return null;
    }

    @Override
    public Integer getPlayersCount(PlayerReq record) {
        return null;
    }

    @Override
    public Result reg(String jsonReq) {
        return null;
    }

    @Override
    public Result login(String jsonReq) {
        System.out.println("登录不能完成，玩家服务连线不成功!");
        return null;
    }

    @Override
    public Result codeLogin(String jsonReq) {
        return null;
    }


    @Override
    public Result quit(String playerId) {
        return null;
    }

    @Override
    public Result quitAccount(String account) {
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

    @Override
    public Result getPlayerByInvite(@RequestBody String invite){
        return null;
    }

    @Override
    public Player getPlayerByPlayerId(String playerId) {
        return null;
    }

    @Override
    public Result getPlayerByAccount(String account) {
        return null;
    }

    @Override
    public Result checkPlayerInvite(String invite) {
        return null;
    }

    @Override
    public Result setTradePassword(Player player) {
        return null;
    }

    @Override
    public void updatePlayer(Player player) {

    }


}
