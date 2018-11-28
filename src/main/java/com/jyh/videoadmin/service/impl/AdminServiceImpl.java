package com.jyh.videoadmin.service.impl;

import com.jyh.videoadmin.mapper.UsersAdminMapper;
import com.jyh.videoadmin.pojo.UsersAdmin;
import com.jyh.videoadmin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author JYH
 * 2018/11/14 16:45
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UsersAdminMapper usersAdminMapper;

    @Override
    public UsersAdmin queryUserForLogin(String username, String password) {
        Example userAdminExample = new Example(UsersAdmin.class);
        Example.Criteria criteria = userAdminExample.createCriteria();
        criteria.andEqualTo("username", username);
        criteria.andEqualTo("password", password);
        UsersAdmin result =usersAdminMapper.selectOneByExample(userAdminExample);

        return result;
    }

    @Override
    public boolean queryUsernameIsExist(String username) {
        UsersAdmin usersAdmin = new UsersAdmin();
        usersAdmin.setUsername(username);
        UsersAdmin result = usersAdminMapper.selectOne(usersAdmin);
        return result == null ? false : true;
    }

    @Override
    public void saveUser(UsersAdmin user) {
        usersAdminMapper.insert(user);
    }
}









































