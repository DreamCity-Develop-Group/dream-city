package com.dream.city.player.service.impl;

import com.alibaba.fastjson.JSON;
import com.dream.city.base.model.CityGlobal;
import com.dream.city.base.model.Page;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Friends;
import com.dream.city.base.model.entity.PlayerGrade;
import com.dream.city.base.model.req.PageReq;
import com.dream.city.base.model.resp.PlayerResp;
import com.dream.city.base.utils.DateUtils;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.mapper.PlayerGradeMapper;
import com.dream.city.base.model.mapper.PlayerMapper;
import com.dream.city.player.service.FriendsService;
import com.dream.city.player.service.PlayerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author Wvv
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerMapper playerMapper;
    @Autowired
    private PlayerGradeMapper gradeMapper;
    @Autowired
    private FriendsService friendsService;

    @Value("${spring.application.name}")
    private String appName;


    @Override
    public Result forgetPwd(String username, String newPwd) {
        PlayerResp player = getPlayerByName(username,null);
        if (player == null){
            return new Result(Boolean.FALSE, CityGlobal.Constant.USER_NOT_EXIT);
        }

        return changePwd(player.getPlayerId(), newPwd);
    }

    @Override
    public Result resetLoginPwd(String username, String oldPwd, String newPwd) {
        String pwdType = "resetLoginPwd";
        Result result = changePwdVelid(username, oldPwd,pwdType);
        if (!result.getSuccess()){
            return result;
        }

        return changePwd(username, newPwd);
    }

    private Result changePwd(String username, String newPwd){
        Player player = new Player();
        player.setPlayerName(username);
        player.setPlayerPass(newPwd);
        int i = playerMapper.updatePassByName(player);
        if (i>0){
            // 修改密码成功
            return new Result(Boolean.TRUE, CityGlobal.Constant.USER_CHANGE_PWD_SUCCESS);
        }
        return new Result(Boolean.FALSE, CityGlobal.Constant.USER_CHANGE_PWD_FAIL);
    }


    @Override
    public Result resetTraderPwd(String username, String oldPwd, String newPwd) {
        String pwdType = "resetTraderPwd";
        Result result = changePwdVelid(username, oldPwd,pwdType);
        if (!result.getSuccess()){
            return result;
        }

        Player player = new Player();
        player.setPlayerName(username);
        player.setPlayerTradePass(newPwd);
        int i = playerMapper.updatePassByName(player);
        if (i>0){
            // 修改密码成功
            return new Result(Boolean.TRUE, CityGlobal.Constant.USER_CHANGE_TRADERPWD_SUCCESS);
        }
        return new Result(Boolean.FALSE, CityGlobal.Constant.USER_CHANGE_TRADERPWD_FAIL);
    }

    private Result changePwdVelid(String username, String oldPwd,String pwdType){
        Player player = new Player();
        player.setPlayerName(username);
        Player playerExit = playerMapper.getPlayerById(player);

        // 用户不存在
        if(playerExit == null){
            return new Result(Boolean.FALSE, CityGlobal.Constant.USER_NOT_EXIT);
        }

        // 旧密码不正确
        if ("resetLoginPwd".equalsIgnoreCase(pwdType) && playerExit.getPlayerPass().equals(oldPwd)){
            return new Result(Boolean.FALSE, CityGlobal.Constant.USER_OLD_PWD_ERROR);
        }
        // 交易密码 没有交易密码的设置交易密码，有交易密码的修改交易密码
        if ("resetTraderPwd".equalsIgnoreCase(pwdType)
                && StringUtils.isNotBlank(playerExit.getPlayerTradePass())
                && playerExit.getPlayerTradePass().equals(oldPwd)){
            return new Result(Boolean.FALSE, CityGlobal.Constant.USER_OLD_PWD_ERROR);
        }

        return new Result(Boolean.TRUE, CityGlobal.Constant.USER_OLD_PWD_ERROR);
    }

    @Override
    @Transactional
    public boolean save(Player player) {
        player.setCreateTime(new Date());
        Integer integer = playerMapper.insertSelective(player);
        return (integer != null && integer>0)?Boolean.TRUE:Boolean.FALSE;
    }

    @Override
    public Integer delete(String playerId) {
        return playerMapper.deleteByPlayerId(playerId);
    }

    @Override
    public Player update(Player player) {
        int i = playerMapper.updateByPlayerId(player);
        return i > 0? player: null;
    }

    @Override
    public Player getPlayer(Player player) {
        return playerMapper.getPlayerById(player);
    }

    @Override
    public Player getPlayerById(String playerId) {
        Player player = new Player();
        if (StringUtils.isBlank(playerId)){
            return null;
        }
        player.setPlayerId(playerId);
        return playerMapper.getPlayerById(player);
    }


    @Override
    public Page getPlayers(PageReq<Map> pageReq) {
        Page page = new Page();
        page.setCondition(pageReq.getCondition());

        Integer count = playerMapper.getPlayersCount(pageReq);
        List<Map> players = playerMapper.getPlayers(pageReq);

        List<Map> playersMap = new ArrayList<>();
        if (!CollectionUtils.isEmpty(players)){
            String getFriendAgree = "添加";
            for (Map player:players){
                if (pageReq.getCondition().containsKey("username")){
                    getFriendAgree = this.getFriendAgree(String.valueOf(pageReq.getCondition().get("username")),player);
                }
                player.put("addfriend",getFriendAgree);
                player.put("friendId","");
                player.put("createTime",DateUtils.str2Date(String.valueOf(player.get("createTime"))));
                playersMap.add(player);
            }
        }
        page.setResult(playersMap);
        page.setTotalCount( count== null?0:count);

        return page;
    }

    private String getFriendAgree(String playerId,Map player){
        String friendId = player.containsKey("player")?String.valueOf(player.get("player")): null;
        Friends record = new Friends();
        record.setPlayerId(playerId);
        record.setFriendId(friendId);
        Integer getFriendAgree = friendsService.getFriendAgree(record);

        String result = "添加";
        if (getFriendAgree == null){
            result = "添加";
        }else {
            if (getFriendAgree == 0){
                result = "已申请";
            } else if (getFriendAgree == 1){
                result = "已添加";
            }
        }
        return result;
    }

    @Override
    public PlayerResp getPlayerByName(String playerName,String playerNick) {
        Player player = new Player();
        if (StringUtils.isNotBlank(playerName)) {
            player.setPlayerName(playerName);
        }
        if (StringUtils.isNotBlank(playerNick)) {
            player.setPlayerNick(playerNick);
        }
        Player playerByName = null;
        if (StringUtils.isNotBlank(playerName) || StringUtils.isNotBlank(playerNick)) {
            playerByName = playerMapper.getPlayerById(player);
        }
        PlayerResp playerResp = null;
        if (playerByName != null){
            playerResp = JSON.toJavaObject(JSON.parseObject(JSON.toJSONString(playerByName)),PlayerResp.class);

            /*这里不用查询级别
            PlayerGrade playerGrade = getPlayerGradeByPlayerId(player.getPlayerId());
            playerResp.setGrade(playerGrade.getGrade());
            playerResp.setCommerceMember(0); //商会成员数 todo
            */
            return playerResp;
        }
        return playerResp;
    }

    @Override
    public Player getPlayerByInvite(String invite){
        Player player = playerMapper.getPlayerByInvite(invite);
        return player;
    }

    @Override
    public Player getPlayerByAccount(String account){
        Player player = playerMapper.getPlayerByAccount(account);
        return player;
    }

    @Override
    public PlayerGrade getPlayerGradeByPlayerId(String playerId) {
        PlayerGrade record = new PlayerGrade();
        record.setPlayerId(playerId);
        return gradeMapper.getPlayerGradeByPlayerId(record);
    }


}
