package com.dream.city.service.impl;

import com.dream.city.base.model.entity.Notice;
import com.dream.city.base.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class ConsumerNoticeServiceImpl{
    @Autowired
    RedisUtils redisUtils;

    public List<Notice> getCacheNotices(){
        List<Notice> notices = new ArrayList<>();
        Map<Object,Object> map =redisUtils.hmget("game_default");
        Iterator<Map.Entry<Object, Object>> iterator = map.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Object, Object> entry = iterator.next();
            Notice notice = (Notice) entry.getValue();
            System.out.println(notice.getNoticeContent());
            notices.add(notice);
        }

        return notices;
    }
}
