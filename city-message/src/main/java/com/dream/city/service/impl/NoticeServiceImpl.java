package com.dream.city.service.impl;

import com.dream.city.base.model.entity.RelationTree;
import com.dream.city.base.model.entity.Notice;
import com.dream.city.base.model.mapper.DictionaryMapper;
import com.dream.city.base.model.mapper.RelationTreeMapper;
import com.dream.city.base.model.mapper.NoticeMapper;
import com.dream.city.base.model.req.NoticeReq;
import com.dream.city.base.service.DictionaryService;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.NoticeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wvv
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeMapper noticeMapper;
    @Autowired
    RelationTreeMapper RelationTreeMapper;
    @Autowired
    DictionaryMapper dictionaryMapper;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    DictionaryService dictionaryService;

    @Override
    public List<RelationTree> testMapper(){
       List<RelationTree> trees =  RelationTreeMapper.getTrees();

        return trees;
    }

    @Override
    public int deleteNoticeById(Integer noticeId) {
        return noticeMapper.deleteByPrimaryKey(noticeId);
    }

    @Override
    public int insertNoticeById(Notice record) {
        return noticeMapper.insertSelective(record);
    }

    @Override
    public Notice getNoticeById(Integer noticeId) {
        return noticeMapper.selectByPrimaryKey(noticeId);
    }

    @Override
    public List<Notice> getNoticeList(Notice record) {
        NoticeReq noticeReq = DataUtils.toJavaObject(record,NoticeReq.class);
        String valByKey = dictionaryService.getValByKey("player.note.getlist.day");
        int getNoteListDay = 0;
        if(StringUtils.isNotBlank(valByKey)){
            getNoteListDay = Integer.parseInt(valByKey);
        }

        noticeReq.setDayParam(getNoteListDay);
        return noticeMapper.getNoticeList(noticeReq);
    }

    @Override
    public int updateNoticeById(Notice record) {
        return noticeMapper.updateByPrimaryKeySelective(record);
    }
}
