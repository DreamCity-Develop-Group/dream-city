package com.dream.city.base.utils;

public final class RedisKeys {

    public static final String REDIS_PRE_KEY = "REDIS_PRE_KEY_";

    public static final String REDIS_KEY_VALIDCODE = REDIS_PRE_KEY + "validCode";

    // 当前登录用户 = CURRENT_USER + username
    public static final String CURRENT_USER = "CURRENT_USER_";

    // 当前登录总人数
    public static final String CURRENT_LOGIN_USER_COUNT = "CURRENT_LOGIN_USER_COUNT_";

}
