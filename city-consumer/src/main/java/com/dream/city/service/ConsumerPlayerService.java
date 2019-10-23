package com.dream.city.service;

import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Page;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerExt;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.model.req.PlayerReq;
import com.dream.city.service.impl.FallBackPlayer;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Wvv
 */
@FeignClient(value = "city-player", fallback = FallBackPlayer.class)
public interface ConsumerPlayerService {

    @RequestMapping("/player/get/{playerId}")
    Result getPlayer(@PathVariable("playerId") String playerId);

    /**
     * 修改玩家头像
     * @param record
     * @return
     */
    @RequestMapping("/player/updatePlayerHeadImg")
    Result<Boolean> updatePlayerHeadImg(@RequestBody PlayerExt record);

    /**
     * 广场玩家列表
     * @param pageReq
     * @return
     */
    @RequestMapping("/player/getPlayers")
    Result<PageInfo> getPlayers(@RequestBody Page pageReq);
    @RequestMapping("/player/getPlayersCount")
    Integer getPlayersCount(@RequestBody PlayerReq record);

    /**
     * 用户注册
     * @param jsonReq
     * @return
     */
    @RequestMapping("/player/reg")
    Result<JSONObject> reg(@RequestBody String jsonReq);


    /**
     * 用户登录
     *
     *
     * @param jsonReq
     * @return
     */
    @RequestMapping("/player/login")
    Result login(@RequestBody String jsonReq);


    /**
     * 用户登录
     */
    @RequestMapping("/player/codelogin")
    Result codeLogin(@RequestBody String jsonReq);


    /**
     * 用户退出
     * @param playerId
     * @return
     */
    @RequestMapping("/player/quit")
    Result quit(@RequestParam("playerId") String playerId);

    /**
     * 退出账号
     *
     * @param account
     * @return
     */
    @RequestMapping("/player/quitAccount")
    Result quitAccount(@RequestParam("account") String account);


    /**
     * 用户忘记密码重置
     */
    @RequestMapping("/player/forgetPwd")
    Result forgetPwd(@RequestParam("username") String username,
                     @RequestParam("newpw") String newpw);



    /**
     * 用户忘记密码重置
     */
    @RequestMapping("/player/resetLoginPwd")
    Result resetLoginPwd(@RequestParam("username") String username,
                         @RequestParam("oldPwd") String oldPwd,
                         @RequestParam("newPwd")  String newPwd);

    /**
     * 重置交易密码
     * @param username
     * @param oldpwshop
     * @return
     */
    @RequestMapping("/player/resetTraderPwd")
    Result resetTraderPwd(@RequestParam("username") String username,
                          @RequestParam("oldpwshop") String oldpwshop,
                          @RequestParam("newpwshop")  String newpwshop);


    /**
     * 根据玩家名称查找玩家
     * @return
     */
    @RequestMapping("/player/getPlayerByName")
    Result<String> getPlayerByName(@RequestBody String jsonReq);

    /**
     * 根据玩家邀请码查找玩家
     * @param invite
     * @return
     */
    @RequestMapping("/player/getPlayerByInvite")
    Result getPlayerByInvite(@RequestParam("invite") String invite);

    /**
     * 根据玩家ID查找玩家
     * @param playerId
     * @return
     */
    @RequestMapping("/player/getPlayerByPlayerId")
    Player getPlayerByPlayerId(@RequestParam("playerId") String playerId);

    /**
     * 根据用户账户查找玩家
     * @param account
     * @return
     */
    @RequestMapping("/player/getPlayerByAccount")
    Result getPlayerByAccount(@RequestParam("account") String account);

    /**
     * 检验邀请码
     * @param invite
     * @return
     */
    @RequestMapping("/player/checkInvite")
    Result checkPlayerInvite(@RequestParam("invite") String invite);

    /**
     * 设置交易密码
     * @param player
     * @return
     */
    @RequestMapping("/player/setTradePassword")
    Result setTradePassword(@RequestBody Player player);


    /**
     * 更新玩家
     * @param player
     */
    @RequestMapping("/player/updatePlayer")
    void updatePlayer(@RequestBody Player player);
}
