package com.jyh.videoadmin.service;


import com.jyh.videoadmin.pojo.UsersAdmin;

/**
 * @author JYH
 * 2018/11/14 16:43
 */
public interface AdminService {

    /**
     * @Description: 用户登录，根据用户名和密码查询用户
     */
    public UsersAdmin queryUserForLogin(String username, String password);


    /**
     * @Description:判断用户名是否存在
     */
    public boolean queryUsernameIsExist(String username);

    /**
     * @Description: 保存用户（用户注册）
     */
    public void saveUser(UsersAdmin user);
}
