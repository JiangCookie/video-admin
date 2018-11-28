package com.jyh.videoadmin.controller;

import com.jyh.videoadmin.common.util.*;
import com.jyh.videoadmin.pojo.UsersAdmin;
import com.jyh.videoadmin.service.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.util.UUID;

/**
 * @author JYH
 * 2018/11/14 16:25
 */
@Controller
@RequestMapping("/manage/admin")
public class AdminController extends BasicController{

    @Autowired
    private AdminService adminService;

    @Autowired
    private RedisShardedPoolUtil redis;

    @GetMapping(value = "/regist")
    public String register(){
        return  "register";
    }

    @PostMapping("/regist")
    public String regist(UsersAdmin user, Model model) throws Exception {

        //1.判断用户名和密码不为空
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            model.addAttribute("JSONResult",JSONResult.errorMsg("用户名和密码不能为空"));
            return "regist";
        }

        // 2. 判断用户名是否存在
        boolean usernameIsExist = adminService.queryUsernameIsExist(user.getUsername());

        // 3. 保存用户，注册信息
        if (!usernameIsExist) {
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
            adminService.saveUser(user);
        } else {
            model.addAttribute("JSONResult",JSONResult.errorMsg("用户名已经存在，请换一个再试"));
            return "regist";
        }

        user.setPassword("");
//        UsersVO usersVO = setUserRedisSessionToken(user);
        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public String login(){
        return  "login";
    }


    @PostMapping("/login")
    @ResponseBody
    public JSONResult userLogin(String username, String password, Model model, HttpServletResponse httpServletResponse, HttpSession session) throws Exception {

        // 1. 判断用户名和密码必须不为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return JSONResult.errorMsg("登陆失败，请重试...");
        }


        // 2. 判断用户是否存在
        UsersAdmin result = adminService.queryUserForLogin(username, MD5Utils.getMD5Str(password));

        // 3. 返回
        if (result != null) {

            //加密密码
            String md5Password = MD5Utils.getMD5Str(result.getPassword());
            UsersAdmin admin = new UsersAdmin();
            admin.setUsername(username);
            admin.setPassword(md5Password);

            //保存登录JsessionId，返回到一级域名
            CookieUtil.writeLoginToken(httpServletResponse,session.getId());
            //根据JsessionId保存到分布式缓存中
            redis.setEx(session.getId(), JSONUtil.obj2String(admin),TIMEOUT);

            return JSONResult.ok();
        } else {
            return JSONResult.errorMsg("用户名或密码不正确, 请重试...");
        }

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        CookieUtil.delLoginToken(httpServletRequest,httpServletResponse);
        redis.del(loginToken);

        return "login";
    }


//    @RequestMapping(value = "get_user_info.do",method = RequestMethod.POST)
//    public String getUserInfo(HttpServletRequest httpServletRequest){
//
//        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
//        if(StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr,User.class);
//
//        if(user != null){
//            return ServerResponse.createBySuccess(user);
//        }
//        return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
//    }
}
