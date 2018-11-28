package com.jyh.videoadmin.controller;

import com.jyh.videoadmin.common.util.PagedResult;
import com.jyh.videoadmin.pojo.Users;
import com.jyh.videoadmin.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author JYH
 * 2018/11/18 16:11
 */
@Controller
@RequestMapping("/manage/user")
public class UserController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/showUserList")
    public String showUserList(){
        return "user/userList";
    }

    @PostMapping("/userList")
    @ResponseBody
    public PagedResult queryBgmList(Users user , Integer page){

        PagedResult result = usersService.queryUsers(user, page == null ? 1 : page, 8);

        return result;
    }
}
