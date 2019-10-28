package com.dream.city.service.consumer.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dream.city.base.utils.RedisKeys;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AuthServiceImpl implements AuthService {
    @Value("${auth.secret}")
    private String SECRET;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    RedisUtils redisUtils;

    /**
     * token 过期时间: 30分钟
     */
    private static final int calendarField = Calendar.MINUTE;
    private static final int calendarInterval = 30;


    /**
     * 获取Token
     * @param username
     * @return
     */
    @Override
    public String generiteToken(String username) {
        String token="";
        try {
            Date date = new Date();
            Calendar nowTime = Calendar.getInstance();
            nowTime.add(calendarField, calendarInterval);
            Date expire = nowTime.getTime();

            Map<String, Object> map = new HashMap<>(10);
            map.put("alg", "HS256");
            map.put("type", "JWT");

            token = JWT.create().withHeader(map)
                    .withClaim("iss", "Service")
                    .withClaim("aud", "APP").withClaim("username", null == username ? null : username.toString())
                    .withIssuedAt(date)
                    .withExpiresAt(expire)
                    .sign(Algorithm.HMAC256(SECRET));


            //设置过期时间
            //redisTemplate.opsForValue().set(RedisKeys.LOGIN_USER_TOKEN+username,token);
            //redisUtils.set(RedisKeys.LOGIN_USER_TOKEN+username,token);
            //redisTemplate.expire(RedisKeys.LOGIN_USER_TOKEN+username,30, TimeUnit.MINUTES);

            redisUtils.set(RedisKeys.LOGIN_USER_TOKEN+username,token,1800);
            redisUtils.expire(RedisKeys.LOGIN_USER_TOKEN+username,1800);
            redisTemplate.expire(RedisKeys.LOGIN_USER_TOKEN+username,1800,TimeUnit.SECONDS);
            return token;
        } catch (IllegalArgumentException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 解密Token
     *
     * @param token
     * @return
     * @throws Exception
     */
    @Override
    public  Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            // e.printStackTrace();
            // token 校验失败, 抛出Token验证非法异常
        }
        return jwt.getClaims();
    }

    /**
     * 根据Token获取username
     *
     * @param token
     * @return user_id
     */
    @Override
    public  String getUsername(String token) {
        Map<String, Claim> claims = verifyToken(token);
        Claim userClaim = claims.get("username");
        if (null == userClaim || StringUtils.isEmpty(userClaim.asString())) {
            // token 校验失败, 抛出Token验证非法异常
        }
        return userClaim.asString();
    }

}
