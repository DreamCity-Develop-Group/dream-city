package com.dream.city.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.CityMessage;
import com.dream.city.base.model.mapper.MessageMapper;
import com.dream.city.base.model.req.MessageReq;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.service.DictService;
import com.dream.city.service.MessageService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Wvv
 */
@Service
public class MessageServiceImpl implements MessageService {


    @Autowired
    MessageMapper messageMapper;
    @Autowired
    DictService dictionaryService;


    @LcnTransaction
    @Transactional
    @Override
    public int insertMessage(CityMessage record) throws BusinessException {
        Integer integer = messageMapper.insertSelective(record);
        return integer == null? 0:integer;
    }

    @LcnTransaction
    @Transactional
    @Override
    public int updateMessageById(CityMessage record) throws BusinessException {
        Integer integer = messageMapper.updateByPrimaryKeySelective(record);
        return integer == null? 0:integer;
    }

    @LcnTransaction
    @Transactional
    @Override
    public int updateMessageHaveReadById(CityMessage record) throws BusinessException {
        CityMessage updateMessage = new CityMessage();
        updateMessage.setId(record.getId());
        updateMessage.setPlayerId(record.getPlayerId());
        updateMessage.setHaveRead(1);
        return updateMessageById(updateMessage);
    }

    @LcnTransaction
    @Transactional
    @Override
    public int deleteMessageById(Long id) throws BusinessException {
        Integer integer = messageMapper.deleteByPrimaryKey(id);
        return integer == null? 0:integer;
    }

    @LcnTransaction
    @Transactional
    @Override
    public CityMessage getMessageById(Long id) throws BusinessException {
        return messageMapper.selectByPrimaryKey(id);
    }

    @LcnTransaction
    @Transactional
    @Override
    public List<CityMessage> getCityMessageList(CityMessage record) throws BusinessException {
        MessageReq messageReq = DataUtils.toJavaObject(record, MessageReq.class);
        int getMsgListDay = 0;
        String valByKey = dictionaryService.getValByKey("player.msg.getlist.day");
        if(StringUtils.isNotBlank(valByKey)){
            getMsgListDay = Integer.parseInt(valByKey);
        }
        messageReq.setDayParam(getMsgListDay);
        return messageMapper.getCityMessageList(messageReq);
    }

    @LcnTransaction
    @Transactional
    @Override
    public int getUnReadCount(String playerId) throws BusinessException {
        Integer count = messageMapper.getUnReadCount(playerId);
        return count == null?0:count;
    }
}
