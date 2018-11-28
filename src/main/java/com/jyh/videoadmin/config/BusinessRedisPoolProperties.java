package com.jyh.videoadmin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author JYH
 * 2018/11/23 10:32
 */
@Configuration
@ConfigurationProperties(prefix ="business.redis")
@PropertySource("classpath:redis.properties")
@Data
public class BusinessRedisPoolProperties {

    private int database;
    private int max_active;

    private int max_wait;

    private int  max_idle;

    private int min_idle;

    private int timeout;

    private String host1;

    private String host2;

    private int port1;

    private int port2;

    private String password1;

    private String password2;


}
