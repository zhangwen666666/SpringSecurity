package com.zw.service.impl;

import com.zw.entity.TRole;
import com.zw.entity.User;
import com.zw.mapper.TRoleMapper;
import com.zw.mapper.UserMapper;
import com.zw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TRoleMapper tRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. 查询数据库，获的用户
        User user = userMapper.selectByLoginAct(username);
        if (user == null) {
            throw new UsernameNotFoundException("账号不存在，登录失败");
        }

        // 2. 给用户配置角色 查询数据库，获得用户的角色列表
        List<TRole> tRoleList = tRoleMapper.selectByUserId(user.getId());
        user.setTRoleList(tRoleList);

        return user;
    }
}
