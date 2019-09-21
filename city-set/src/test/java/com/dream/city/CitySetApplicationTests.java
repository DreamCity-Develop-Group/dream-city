package com.dream.city;

import com.dream.city.base.model.entity.Notice;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.domain.mapper.NoticeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CitySetApplicationTests {
    @Autowired
    NoticeMapper noticeMapper;
    @Autowired
    RedisUtils redisUtils;
    /*@Test
    public void contextLoads() {
    }*/

    @Test
    public void addNotices(){
        List<Notice> notices = noticeMapper.getGameNotices(1);
        HashMap<String,Notice> noticesMap = new HashMap<>();
        for (Notice notice: notices){
            noticesMap.put("notice-"+notice.getNoticeId(),notice);
        }

        redisUtils.hmset("game_default",noticesMap);

        Map<Object,Object> map =redisUtils.hmget("game_default");

        map.get("notice-1");

        for (Object map1 : map.entrySet()){
            Notice notice = (Notice)map1;
            System.out.println(notice.getNoticeContent());
        }

    }

}
