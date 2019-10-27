package com.dream.city.service.routes;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import org.springframework.web.bind.annotation.RequestBody;

public interface AccountService {
    Message getPlayerAccount(Message msg);
}
