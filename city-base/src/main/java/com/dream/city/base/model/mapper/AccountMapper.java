package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.req.PlayerAccountReq;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Wvv
 */
@Mapper
public interface AccountMapper {

    Integer deleteByPrimaryKey(Integer accId);

    Integer insertSelective(PlayerAccount record);

    //PlayerAccount updateByPrimaryKey(Integer accId);

    Integer updateByPrimaryKeySelective(PlayerAccount account);

    //Integer insert(PlayerAccount account);

    PlayerAccount selectByPrimaryKey(Integer accId);

    PlayerAccount getPlayerAccountSelective(PlayerAccount record);

    /**
     * 获取平台账户
     * @param record
     * @return
     */
    List<PlayerAccount> getPlatformAccounts(PlayerAccount record);

    Integer updateByPlayerId(PlayerAccount record);


    /**
     * 获取平台账户
     * @param record
     * @return
     */
    List<PlayerAccount> getPlatformAccounts(PlayerAccountReq record);

    List<PlayerAccount> getPlayerAccountList(PlayerAccount record);

    PlayerAccount getPlayerAccount(String accPlayerId);
}