package com.dream.city;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityConsumerApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void testUserReg() {

        MessageData data = new MessageData("login", "user");
        Map<String, String> map = new HashMap<>();
        map.put("username", "Wvv11");
        map.put("userpass", "123456");
        map.put("code", "125478");
        map.put("nick", "VVV");
        map.put("invite", "saas223");
        data.setT(map);
        System.out.println(data);
        Message msg = new Message();
        msg.setData(data);

        System.out.println(msg);

        //////////////////////////////////////////
//        MessageData data1 = new MessageData(map);
//        System.out.println(data1);
//        Message message = new Message(data1);
//        System.out.println(message);
//
//        Message message1 = new Message(new MessageData(map));
//        System.out.println(message1);


    }

    @Test
    public void testUUID() {

    }

}
