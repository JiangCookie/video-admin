package com.jyh.videoadmin.controller;

import com.jyh.videoadmin.common.util.RedisShardedPoolUtil;
import com.jyh.videoadmin.config.RedisShardedPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.ShardedJedis;

import javax.servlet.http.HttpSession;

/**
 * @author JYH
 * 2018/11/14 19:35
 */
@Controller
@RequestMapping("/manage")
public class initController {

    @GetMapping(value = "/index")
    public String index(HttpSession httpSession) {
        httpSession.getId();
        return  "index";
    }




    @GetMapping(value = "/index_v3")
    public String index_v3(){
        return  "index_v3";
    }
}
