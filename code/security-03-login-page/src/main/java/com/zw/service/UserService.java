package com.zw.service;

import org.springframework.security.core.userdetails.UserDetailsService;

// 我们处理登录的service接口，需要集成SpringSecurity框架的UserDetailsService接口
public interface UserService extends UserDetailsService {
}
