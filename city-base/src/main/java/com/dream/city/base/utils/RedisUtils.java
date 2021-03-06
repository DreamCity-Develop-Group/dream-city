package com.dream.city.base.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.util.CollectionUtils;

import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * @author wvv
 * Redis客户端
 */
//@Component
public class RedisUtils {
    private static final Charset CODE = Charset.forName("UTF-8");

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;



    public boolean hasKey(String key){
       return (boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
               return  redisConnection.exists(key.getBytes());
            }
        });
    }

    public void delete(String key){
        redisTemplate.delete(key);
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                stringRedisTemplate.delete(key[0]);
                //redisTemplate.getConnectionFactory().getConnection().del(key[0].getBytes());
            } else {
                stringRedisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    public void set(String key, String value) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    public void set(String key, final String value,final long expireTime) {
       redisTemplate.execute((RedisCallback) redisConnection -> {
           redisConnection.set(key.getBytes(),value.getBytes());
           return null;
       });
    }


    public Optional<String> get( String key) {
        String result = (String) redisTemplate.execute((RedisCallback) redisConnection -> {
            String result1 = null;
            try {
                result1 = new String(redisConnection.get(key.getBytes()));
            }catch (Exception e){
                return null;
            }
            return result1;

        });
        return result == null ? Optional.empty() : Optional.of(result.toString());
    }


    public void setStr(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }


    public String getStr(String key){
        if(StringUtils.isBlank(key)){
            return null;
        }
        String value = stringRedisTemplate.opsForValue().get(key);

        return value;
    }


    public void hashSet(String key, String field, String value) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put(key, field, value);
    }

    public void hashSet(String key, String field, Object value) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put(key, field, value);
    }


    public Optional<String> hashGet(String key, String field) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        Object result = hashOperations.get(key, field);
        return result == null ? Optional.empty() : Optional.of(result.toString());
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            stringRedisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hget(String key, String item) {
        return stringRedisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hmget(String key) {
        Map<Object, Object> map = stringRedisTemplate.opsForHash().entries(key);
        return map;
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value) {
        try {
            stringRedisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value, long time) {
        try {
            stringRedisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, Object... item) {
        stringRedisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return stringRedisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     */
    public double hincr(String key, String item, double by) {
        return stringRedisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return
     */
    public double hdecr(String key, String item, double by) {
        return stringRedisTemplate.opsForHash().increment(key, item, -by);
    }

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
                redisTemplate.expire(key,time,TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key) {
        return stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
    }


    //============================set=============================

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    public Set<String> sGet(String key) {
        try {
            return stringRedisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(String key, Object value) {
        try {
            return stringRedisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    //===============================list=================================



    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, String value) {
        try {
            stringRedisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean lSet(String key, String value, long time) {
        try {
            stringRedisTemplate.opsForList().rightPush(key, value);
            if (time > 0)
            { expire(key, time);}
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    /*public boolean lSet(String key, List<String> value) {
        try {
            stringRedisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }*/
    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean publish(String channel, JSONObject value) {
        try {
            redisTemplate.convertAndSend(channel,value);
            //stringRedisTemplate.convertAndSend(channel,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean publishMsg(String channel, String value) {
        try {
            redisTemplate.convertAndSend(channel,value);
            //stringRedisTemplate.convertAndSend(channel,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean lpush(String key, JSONObject value) {
        try {
            //jedis.lpush(key, value.toString());
            redisTemplate.opsForList().leftPush(key,value.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean lpush(String key, String value) {
        try {
            //jedis.lpush(key, value.toString());
            redisTemplate.opsForList().leftPush(key,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public Object lpop(String key){
        try {
            Object object = redisTemplate.opsForList().leftPop(key);
            return object;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public Object rpop(String key){
        try {
            Object object = redisTemplate.opsForList().rightPop(key);
            return object;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }


    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean lSet(String key, List<String> value, long time) {
        try {
            stringRedisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0){ expire(key, time);}
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束  0 到 -1代表所有值
     * @return
     */
    public List<String> lGet(String key, long start, long end) {
        try {
            return stringRedisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return
     */
    public long lGetListSize(String key) {
        try {
            return stringRedisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object lGetIndex(String key, long index) {
        try {
            return stringRedisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean lUpdateIndex(String key, long index, String value) {
        try {
            stringRedisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = stringRedisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /*public Long incr(String key, long liveTime) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();

        //初始设置过期时间
        if ((null == increment || increment.longValue() == 0) && liveTime > 0) {
            entityIdCounter.expire(liveTime, TimeUnit.SECONDS);
        }

        return increment;
    }*/

    public Long incr(String key) {
        return incr(key, 1L);
    }
    public Long decr(String key) {
        return incr(key, -1L);
    }
    public Long incr(String key, long add) {
        return stringRedisTemplate.execute((RedisCallback<Long>) con -> {
            Long record = con.incrBy(toBytes(key), add);
            return record == null ? 0L : record;
        });
    }

    private byte[] toBytes(String key) {
        nullCheck(key);
        return key.getBytes(CODE);
    }

    private void nullCheck(Object... args) {
        for (Object obj : args) {
            if (obj == null) {
                throw new IllegalArgumentException("redis argument can not be null!");
            }
        }
    }



    public boolean addOnlinePlayer(String playerName,Integer id) {
        boolean success = redisTemplate.opsForZSet().add("ONLINE_PLAYERS",playerName,id);
        return success;
    }

    public long getOnlinePlayerCount(){
        long count = redisTemplate.opsForZSet().zCard("ONLINE_PLAYERS");
        return count;
    }

    public boolean rmOnlinePlayer(String playerName) {
        Long id = redisTemplate.opsForZSet().remove("ONLINE_PLAYERS",playerName);
        return id>0;
    }

    public boolean isOnlinePlayer(String playerName) {
        Double score = redisTemplate.opsForZSet().score("ONLINE_PLAYERS",playerName);
        if (null == score){
            return false;
        }
        return score.compareTo(new Double(0))>0;
    }

}
