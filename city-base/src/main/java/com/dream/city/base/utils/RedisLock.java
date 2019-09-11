package com.dream.city.base.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Component
public class RedisLock implements Lock {

    @Autowired
    private Jedis jedis;

    private static String LOCK_NAME = "REDIS_LOCK";
    private static String REQUEST_ID = "1234567890";


    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        while (true){
            Long setnx = jedis.setnx(LOCK_NAME, REQUEST_ID);
            if (setnx == 1){
                return true;
            }
        }
    }

    @Override
    public void lock() {
        if (tryLock()){
            jedis.set(LOCK_NAME, REQUEST_ID,"NX","PX",6000);
        }
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        String script = "if redis.call('get',KEY[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
        jedis.eval(script, Collections.singletonList(LOCK_NAME),Collections.singletonList(REQUEST_ID));
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
