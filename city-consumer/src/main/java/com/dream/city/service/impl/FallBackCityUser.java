package com.dream.city.service.impl;

import com.dream.city.domain.Message;
import com.dream.city.service.CityUserService;
import org.springframework.stereotype.Component;

/**
 * @author Wvv
 */
@Component
public class FallBackCityUser implements CityUserService {
    @Override
    public String userIndex(String str) {
        return "error";
    }

    @Override
    public String users(String id) {
        return "error id";
    }

    @Override
    public String reg(Message message) {
        return null;
    }

    @Override
    public String login(Message message) {
        return null;
    }

    @Override
    public String quit(Message message) {
        return null;
    }

    @Override
    public String reset(Message message) {
        return null;
    }
}
