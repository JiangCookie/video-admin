package com.jyh.videoadmin.controller.common;

import com.jyh.videoadmin.common.util.CookieUtil;
import com.jyh.videoadmin.common.util.JSONUtil;
import com.jyh.videoadmin.common.util.RedisShardedPoolUtil;
import com.jyh.videoadmin.config.RedisShardedPool;
import com.jyh.videoadmin.pojo.UsersAdmin;
import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 采用过滤器方式重置session过期时间
 * @author JYH
 * 2018/11/25 18:09
 */
@WebFilter(urlPatterns = "/*",filterName = "sessionExpireFilter")
public class SessionExpireFilter implements Filter {

    @Autowired
    private RedisShardedPoolUtil redisShardedPoolUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;

        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        if(StringUtils.isNotEmpty(loginToken)){
            //判断logintoken是否为空或者""
            //如果不为空的话，符合条件，继续拿user信息
            String userJsonSr = redisShardedPoolUtil.get(loginToken);
            UsersAdmin user = JSONUtil.String2Obj(userJsonSr,UsersAdmin.class);
            if(user != null){
                //如果user不为空，则重置session的时间，即调用expire命令
                redisShardedPoolUtil.expire(loginToken,60*60*24*30);
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
