package com.jyh.videoadmin.config;

import com.jyh.videoadmin.config.BusinessRedisPoolProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JYH
 * 2018/11/23 10:50
 */
@Configuration
@Slf4j
public class RedisShardedPool {

    @Autowired
    private BusinessRedisPoolProperties redisPro;

    /**
     * sharded jedis连接池
     */
    private static ShardedJedisPool pool;

    /**
     * @Description: 获取Jedis实例
     */
    @Bean
    public ShardedJedisPool initPool(){


        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(redisPro.getMax_active());
            config.setMaxIdle(redisPro.getMax_idle());
            config.setMinIdle(redisPro.getMin_idle());
            //连接耗尽的时候，是否阻塞，false会抛出异常，true阻塞直到超时。默认为true。
            config.setBlockWhenExhausted(true);
            // 设置连接池没有连接后客户端的最大等待时间 ( 单位为毫秒 )
            config.setMaxWaitMillis(3000);

            JedisShardInfo info1 = new JedisShardInfo(redisPro.getHost1(),redisPro.getPort1(),1000*2);
            info1.setPassword(redisPro.getPassword1());
            JedisShardInfo info2 = new JedisShardInfo(redisPro.getHost2(),redisPro.getPort2(),1000*2);
            info2.setPassword(redisPro.getPassword2());

            List<JedisShardInfo> jedisShardInfoList = new ArrayList<JedisShardInfo>(2);

            jedisShardInfoList.add(info1);
            jedisShardInfoList.add(info2);

            pool = new ShardedJedisPool(config,jedisShardInfoList, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);

            return pool;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }



    /**
     * 获取Jedis对象不再是直接生成一个Jedis对象，而是直接从连接池里获取
     * @return
     */
    public static ShardedJedis getJedis(){
        return pool.getResource();
    }


}
