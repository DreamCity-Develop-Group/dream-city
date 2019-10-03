package com.dream.city.service.impl;

import com.dream.city.base.model.entity.CityMessage;
import com.dream.city.base.model.mapper.MessageMapper;
import com.dream.city.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wvv
 */
@Service
public class MessageServiceImpl implements MessageService {


    @Autowired
    MessageMapper messageMapper;


    @Override
    public int insertMessage(CityMessage record) {
        Integer integer = messageMapper.insertSelective(record);
        return integer == null? 0:integer;
    }

    @Override
    public int updateMessageById(CityMessage record) {
        Integer integer = messageMapper.updateByPrimaryKeySelective(record);
        return integer == null? 0:integer;
    }

    @Override
    public int updateMessageHaveReadById(CityMessage record) {
        CityMessage updateMessage = new CityMessage();
        updateMessage.setId(record.getId());
        updateMessage.setPlayerId(record.getPlayerId());
        updateMessage.setHaveRead(1);
        return updateMessageById(updateMessage);
    }

    @Override
    public int deleteMessageById(Long id) {
        Integer integer = messageMapper.deleteByPrimaryKey(id);
        return integer == null? 0:integer;
    }

    @Override
    public CityMessage getMessageById(Long id) {
        return messageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CityMessage> getCityMessageList(CityMessage record) {
        return messageMapper.getCityMessageList(record);
    }
}
