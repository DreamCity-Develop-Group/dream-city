package com.dream.city.service.impl;

import com.dream.city.base.model.entity.RelationTree;
import com.dream.city.base.model.entity.Notice;
import com.dream.city.base.model.entity.RelationTree;
import com.dream.city.base.model.mapper.RelationTreeMapper;
import com.dream.city.base.model.mapper.NoticeMapper;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.NoticeService;
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
    RedisUtils redisUtils;


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
        return noticeMapper.getNoticeList(record);
    }

    @Override
    public int updateNoticeById(Notice record) {
        return noticeMapper.updateByPrimaryKeySelective(record);
    }
}
