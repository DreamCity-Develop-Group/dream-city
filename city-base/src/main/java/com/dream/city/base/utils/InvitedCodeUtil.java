package com.dream.city.base.utils;

import java.util.UUID;

public class InvitedCodeUtil {

    public static final String getCode(){
        String uuid = UUID.randomUUID().toString();

        String code = uuid.replace("-", "").substring(4,10);
        return code;
    }
}
