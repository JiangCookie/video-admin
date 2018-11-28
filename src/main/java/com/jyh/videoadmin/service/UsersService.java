package com.jyh.videoadmin.service;

import com.jyh.videoadmin.common.util.PagedResult;
import com.jyh.videoadmin.pojo.Users;

/**
 * @author JYH
 * 2018/11/18 16:30
 */
public interface UsersService {

    public PagedResult queryUsers(Users user , Integer page, Integer pageSize);
}
