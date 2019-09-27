package com.dream.city.invest.domain.mapper;


import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.req.PlayerAccountReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlayerAccountMapper {

    Integer deleteByPrimaryKey(Integer accId);

    Integer insertSelective(PlayerAccount record);

    PlayerAccount getPlayerAccount(PlayerAccountReq record);

    /**
     * 获取平台账户
     * @param record
     * @return
     */
    List<PlayerAccount> getPlatformAccounts(PlayerAccountReq record);

    Integer updateByPlayerId(PlayerAccount record);

    List<PlayerAccount> getPlayerAccountList(PlayerAccount record);

}