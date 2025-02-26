package com.zw.service.impl;

import com.zw.entity.TUser;
import com.zw.mapper.TUserMapper;
import com.zw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TUserMapper tUserMapper;

    /**
     * 这个方法是在UserDetailsService接口中定义的
     * 该方法会在SpringSecurity框架登录的时候登录
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询数据库，查询页面传递的用户名是否在数据库中存在
        TUser tUser = tUserMapper.selectByLogiznAct(username);
        if (tUser == null) {
            // UsernameNotFoundException异常是SpringSecurity框架提供的
            throw new UsernameNotFoundException("登录账号不存在");
        }

        return tUser;
    }
}
