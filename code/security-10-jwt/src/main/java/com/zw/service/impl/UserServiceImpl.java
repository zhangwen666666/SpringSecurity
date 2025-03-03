package com.zw.service.impl;

import com.zw.entity.Permission;
import com.zw.entity.User;
import com.zw.mapper.PermissionMapper;
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
    private PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectByLoginAct(username);
        if (user == null){
            throw new UsernameNotFoundException("账号不存在");
        }

        // 查询用户的权限code列表 （一个用户可能有多个权限code）
        // select tp.* from t_permission tp
        // left join t_role_permission trp on tp.id = trp.permission_id
        // left join t_role tr on tr.id = trp.role_id
        // left join t_user_role tur on tr.id = tur.role_id
        // left join t_user tu on tur.user_id = tu.id
        // where tu.id = #{userId}
        List<Permission> permissionList = permissionMapper.selectByUserId(user.getId());
        user.setPermissionList(permissionList);

        return user;
    }
}
