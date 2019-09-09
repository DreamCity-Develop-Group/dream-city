package com.dream.city.service.impl;

import com.dream.city.domain.entity.Player;
import com.dream.city.domain.mapper.PlayerMapper;
import com.dream.city.domain.mapper.UserMapper;
import com.dream.city.domain.entity.User;
import com.dream.city.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Wvv
 */
@Service
public class UserServiceImpl implements UserService {

//    @Autowired
//    UserMapper userMapper;
    @Autowired
    PlayerMapper playerMapper;

    @Value("${spring.application.name}")
    private String appName;

    @Override
    public boolean saveUser(User user) {
        Player record = getPlayer(user);
        return playerMapper.insertPlayer(record)>0?Boolean.TRUE:Boolean.FALSE;
    }

    @Override
    public void deleteUser(String playerId) {
        playerMapper.deleteByPlayerId(playerId);
    }

    @Override
    public User updateUser(User user) {
        playerMapper.updateByPlayerId(getPlayer(user));
        return user;
    }

    @Override
    public List<User> getUsers(User user) {
        Player playerReq = getPlayer(user);
        List<Player> players = playerMapper.getPlayers(playerReq);

        List<User> users = new ArrayList<>();
        if (CollectionUtils.isEmpty(players)){
            return users;
        }

        players.forEach(player -> {
            users.add(getUser(player));
        });
        return users;
    }


    private Player getPlayer(User user){
        Player record = new Player();
        record.setPlayerId(UUID.randomUUID().toString().replace("-",""));
        record.setCreateDate(new Date());
        record.setInvited(user.getUInvite());
        record.setNick(user.getUNick());
        record.setUsername(user.getUName());
        record.setUserpass(user.getUPass());//todo  加密

        return record;
    }

    private User getUser(Player player){
        User record = new User();
        record.setUId(player.getPlayerId());
        record.setUInvite(player.getInvited());
        record.setUName(player.getUsername());
        record.setUNick(player.getNick());
        //record.setUPass();

        return record;
    }



}
