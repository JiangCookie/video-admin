package com.jyh.videoadmin.common.util;

import com.jyh.videoadmin.config.RedisShardedPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.ShardedJedis;

import java.util.concurrent.TimeUnit;

/**
 * @author JYH
 * 2018/11/23 14:53
 */
@Slf4j
@Component
public class RedisShardedPoolUtil {


    /**
     * 实现命令：SET key value，设置一个key-value（将字符串值 value关联到 key）
     *
     * @param key
     * @param value
     */
    public String set(String key,String value){
        ShardedJedis jedis = null;
        String result = null;

        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.set(key,value);
        } catch (Exception e) {
            log.error("set key:{} value:{} error",key,value,e);
            return result;
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return result;
    }

    /**
     * 实现命令：SET key value EX seconds，设置key-value和超时时间（秒）
     *
     * @param key
     * @param value
     * @param exTime （以秒为单位）
     */
    public String setEx(String key, String value, int exTime) {
        ShardedJedis jedis = null;
        String result = null;

        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.setex(key,exTime,value);
        } catch (Exception e) {
            log.error("setEx key:{} value:{} exTime:{} error",key,value,exTime,e);
            return result;
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return result;
    }

    /**
     * 实现命令：expire 设置过期时间，单位秒
     * @param key
     * @param exTime
     * @return
     */
    public Long expire(String key, int exTime) {
        ShardedJedis jedis = null;
        Long result = null;

        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.expire(key,exTime);
        } catch (Exception e) {
            log.error("expire key:{} error",key,e);
            return result;
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return result;
    }


    /**
     * 实现命令：GET key，返回 key所关联的字符串值。
     *
     * @param key
     * @return value
     */
    public String get(String key) {
        ShardedJedis jedis = null;
        String result = null;
        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.get(key);
        } catch (Exception e) {
            log.error("get key:{} error",key,e);
            return result;
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return result;
    }

    /**
     * 实现命令：DEL key，删除一个key
     *
     * @param key
     */
    public Long del(String key) {
        ShardedJedis jedis = null;
        Long result = null;
        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.del(key);
        } catch (Exception e) {
            log.error("del key:{} error",key,e);
            return result;
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return result;
    }
    
}
