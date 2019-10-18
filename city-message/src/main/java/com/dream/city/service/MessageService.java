package com.dream.city.service;


import com.dream.city.base.model.entity.CityMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Wvv
 */
public interface MessageService {

    int insertMessage(CityMessage record);

    int updateMessageById(CityMessage record);

    int updateMessageHaveReadById(CityMessage record);

    int deleteMessageById(Long id);

    CityMessage getMessageById(Long id);

    List<CityMessage> getCityMessageList(CityMessage record);

    int getUnReadCount(String playerId);
}