package com.dream.city.player.service.impl;

import com.alibaba.fastjson.JSON;
import com.dream.city.base.model.CityGlobal;
import com.dream.city.base.model.Page;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Friends;
import com.dream.city.base.model.entity.PlayerGrade;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.model.resp.PlayerResp;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.mapper.PlayerGradeMapper;
import com.dream.city.base.model.mapper.PlayerMapper;
import com.dream.city.player.service.FriendsService;
import com.dream.city.player.service.PlayerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
            return Result.result(Boolean.FALSE, CityGlobal.Constant.USER_NOT_EXIT);
        }

        return changePwd(player.getPlayerId(), newPwd);
    }

    @Override
    public Result resetLoginPwd(String username, String oldPwd, String newPwd) {
        String pwdType = "resetLoginPwd";
        PlayerResp player = getPlayerByName(username,null);
        Result result = changePwdValid(username, oldPwd,pwdType);
        if (!result.getSuccess()){
            return result;
        }

        return changePwd(player.getPlayerId(), newPwd);
    }

    private Result changePwd(String playerId, String newPwd){
        Player player = new Player();
        player.setPlayerId(playerId);
        player.setPlayerPass(newPwd);
        int i = playerMapper.updateByPlayerId(player);
        if (i>0){
            // 修改密码成功
            return Result.result(Boolean.TRUE, CityGlobal.Constant.USER_CHANGE_PWD_SUCCESS);
        }
        return Result.result(Boolean.FALSE, CityGlobal.Constant.USER_CHANGE_PWD_FAIL);
    }


    @Override
    public Result resetTraderPwd(String username, String oldpwshop, String newpwshop) {
        String pwdType = "resetTraderPwd";
        Result result = changePwdValid(username, oldpwshop,pwdType);
        if (!result.getSuccess()){
            return result;
        }

        Player player = new Player();
        player.setPlayerName(username);
        player.setPlayerTradePass(newpwshop);
        int i = playerMapper.updatePassByName(player);
        if (i>0){
            // 修改密码成功
            return Result.result(Boolean.TRUE, CityGlobal.Constant.USER_CHANGE_TRADERPWD_SUCCESS,ReturnStatus.SUCCESS.getStatus());
        }
        return Result.result(Boolean.FALSE, CityGlobal.Constant.USER_CHANGE_TRADERPWD_FAIL,ReturnStatus.FAILED.getStatus());
    }

    private Result changePwdValid(String username, String oldpwshop,String pwdType){
        Player player = new Player();
        player.setPlayerName(username);
        PlayerResp playerExit = playerMapper.getPlayerById(player);

        // 用户不存在
        if(playerExit == null){
            return Result.result(Boolean.FALSE, CityGlobal.Constant.USER_NOT_EXIT,ReturnStatus.ERROR_NOTEXISTS.getStatus());
        }

        // 旧密码不正确
        if ("resetLoginPwd".equalsIgnoreCase(pwdType) && !playerExit.getPlayerPass().equals(oldpwshop)){
            return Result.result(Boolean.FALSE, CityGlobal.Constant.USER_OLD_PWD_ERROR, ReturnStatus.ERROR_PASS.getStatus());
        }
        // 交易密码 没有交易密码的设置交易密码，有交易密码的修改交易密码
        if ("resetTraderPwd".equalsIgnoreCase(pwdType)
                && StringUtils.isNotBlank(playerExit.getPlayerTradePass())
                && !playerExit.getPlayerTradePass().equals(oldpwshop)){
            return Result.result(Boolean.FALSE, CityGlobal.Constant.USER_OLD_PWD_ERROR,ReturnStatus.ERROR_PASS.getStatus());
        }

        return Result.result(Boolean.TRUE, CityGlobal.Constant.USER_CHANGE_PWD_SUCCESS,ReturnStatus.SUCCESS.getStatus());
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
    public PlayerResp getPlayer(Player player) {
        return playerMapper.getPlayerById(player);
    }

    @Override
    public PlayerResp getPlayerById(String playerId) {
        Player player = new Player();
        if (StringUtils.isBlank(playerId)){
            return null;
        }
        player.setPlayerId(playerId);
        return playerMapper.getPlayerById(player);
    }


    @Override
    public PageInfo<PlayerResp> getPlayers(Page pageReq) {
        Player playerReq = DataUtils.toJavaObject(pageReq.getCondition(),Player.class);
        PageHelper.startPage(pageReq.getPageNum(),pageReq.getPageSize());
        List<PlayerResp> players = playerMapper.getPlayers(playerReq);
        if (!CollectionUtils.isEmpty(players)){
            String getFriendAgree = "添加";
            for (int i=0;i<players.size();i++){
                if (StringUtils.isNotBlank(playerReq.getPlayerId())){
                    getFriendAgree = this.getFriendAgree(playerReq.getPlayerId(),players.get(i));
                }
                players.get(i).setAddfriend(getFriendAgree);
                if (players.get(i).getAgree() == null){
                    players.get(i).setAgree(0);
                }
            }
        }
        return new PageInfo<>(players);
    }

    private String getFriendAgree(String playerId,PlayerResp player){
        String friendId = player.getPlayerId();
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
        PlayerResp playerByName = null;
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

    @Override
    public Player getPlayerByPlayerId(String playerId) {
        return playerMapper.getPlayer(playerId);
    }

    @Override
    public boolean setTraderPwd(String playerId, String tradePass){
        try {
            Player player = playerMapper.getPlayer(playerId);
            player.setPlayerTradePass(tradePass);
            playerMapper.updateByPlayerId(player);
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
