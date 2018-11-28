package com.jyh.videoadmin;

import com.jyh.videoadmin.controller.common.AuthorityInterceptor;
import com.jyh.videoadmin.controller.common.SessionExpireFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@MapperScan("com.jyh.videoadmin.mapper")
/**
 * @ServletComponentScan
 * Servlet、Filter、Listener 可以直接通过 @WebServlet、@WebFilter、@WebListener 注解自动注册
 */
@ServletComponentScan
public class VideoAdminApplication implements WebMvcConfigurer {

    public static void main(String[] args){
        SpringApplication.run(VideoAdminApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> path = new ArrayList<>();
        path.add("/manage/admin/login");
        path.add("/manage/admin/regist");

        registry.addInterceptor(new AuthorityInterceptor())
                /**
                 * /* 是拦截所有的文件夹，不包含子文件夹
                 * /** 是拦截所有的文件夹及里面的子文件夹
                 */
                .addPathPatterns("/manage/**")
                .excludePathPatterns(path);
    }


//    /**
//     * Filter注入
//     * @return
//     */
//    @Bean
//    public FilterRegistrationBean  sessionFilter(){
//        FilterRegistrationBean frBean = new FilterRegistrationBean();
//        frBean.setFilter(new SessionExpireFilter());
//        frBean.addUrlPatterns("/*");
//
//
//        return frBean;
//    }
}
