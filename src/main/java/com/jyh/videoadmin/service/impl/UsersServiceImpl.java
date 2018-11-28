package com.jyh.videoadmin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyh.videoadmin.common.util.PagedResult;
import com.jyh.videoadmin.mapper.UsersMapper;
import com.jyh.videoadmin.pojo.Users;
import com.jyh.videoadmin.service.UsersService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author JYH
 * 2018/11/18 16:32
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public PagedResult queryUsers(Users user, Integer page, Integer pageSize) {

        String username = "";
        String nickname = "";

        if(user != null){
            username = user.getUsername();
            nickname = user.getNickname();
        }

        PageHelper.startPage(page,pageSize);

        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(username)){
            criteria.andLike("username","%" + username + "%");
        }
        if(StringUtils.isNotBlank(nickname)){
            criteria.andLike("nickname","%" + nickname + "%");
        }
        List<Users> userList = usersMapper.selectByExample(example);

        PageInfo<Users> pageInfo = new PageInfo<>(userList);

        PagedResult grid = new PagedResult();
        grid.setTotal(pageInfo.getPages());
        grid.setRows(userList);
        grid.setPage(page);
        grid.setRecords(pageInfo.getTotal());

        return grid;
    }
}
