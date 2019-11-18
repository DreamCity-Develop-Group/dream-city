package com.dream.city.base.utils;

/**
 * @author nh
 */
public final class RedisKeys {

    public static final String REDIS_PRE_KEY = "REDIS_PRE_KEY_";

    public static final String REDIS_KEY_VALIDCODE = REDIS_PRE_KEY + "validCode";

    // 当前登录用户 = CURRENT_USER + username
    public static final String CURRENT_USER = "CURRENT_USER_";

    public static final String LOGIN_USER_TOKEN = "LOGIN_USER_TOKEN_";

    public static final String INVERST_HASH_DATA = "INVERST_HASH_DATA_";

    public static final String MAIN_HASH_DATA ="MAIN_HASH_DATA_";

    // 当前登录总人数
    public static final String CURRENT_LOGIN_USER_COUNT = "CURRENT_LOGIN_USER_COUNT_";


    //广场玩家列表 换一批
    public static final String SQUARE_PLAYER_LIST_ANOTHER_BATCH = "ANOTHER_BATCH_PLAYER_";

    //玩家在线状态
    public static final String PLAYER_ONLINE_STATE_KEY = "clientID-";

    //玩家消息通道
    public static final String PLAYER_MESSAGE_CHANNEL = "PUSHER_CHANNEL";
}
