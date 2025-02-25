package com.zw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 配置SpringSecurity框架的一些行为
    // 配置我们自己的登录页，不使用框架默认的登录页
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception { // 安全过滤器Bean
        // SecurityFilterChain是一个接口，DefaultSecurityFilterChain是实现类
        // 在SpringSecurity框架开发时，不是直接new DefaultSecurityFilterChain
        // return new DefaultSecurityFilterChain();

        // httpSecurity是Spring容器中的一个Bean，通过参数注入进来
        return httpSecurity
                // 配置我们自己的登录页
                .formLogin(t -> {
                    t.loginPage("/toLogin");// 定制登录页 (Thymeleaf页面)
                })
                .build();
    }
}
