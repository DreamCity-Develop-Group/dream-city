package com.dream.city;

import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.utils.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityWorkerApplicationTests {

    @Test
    public void contextLoads() {
        Message message = new Message(
                "server",
                "client",
                new MessageData("push","comm",new JSONObject(),200),
                "升级成功"
        );
        JSONObject upgrade = new JSONObject();
        upgrade.put("level",22);

        message.getData().setData(upgrade);
        String json = JsonUtil.parseObjToJson(message);
        JSONObject ret = JSONObject.parseObject(json);
        Map map = null;
        try {
            map = JsonUtil.objectToMap(message);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(map);

    }


}
