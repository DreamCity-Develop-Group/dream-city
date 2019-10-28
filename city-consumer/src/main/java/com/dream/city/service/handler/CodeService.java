package com.dream.city.service.handler;

import com.dream.city.base.model.Result;

public interface CodeService {
    Result checkCode(String code, String phone);
}
