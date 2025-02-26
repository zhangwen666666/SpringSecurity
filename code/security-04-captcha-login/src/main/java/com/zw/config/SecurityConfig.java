package com.zw.config;

import com.zw.filter.CaptchaFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CaptchaFilter captchaFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // 配置我们自己的登录页
                .formLogin(t -> {
                    t.loginProcessingUrl("/user/login")
                     .loginPage("/toLogin");// 定制登录页 (Thymeleaf页面)
                })
                .authorizeHttpRequests(t -> {
                    t.requestMatchers("/toLogin", "/common/captcha").permitAll() // 特殊情况，允许不登录就可以访问
                     .anyRequest().authenticated(); // 任何请求都需要认证
                })
                // 将验证码过滤器添加到接收登录账号密码的filter之前
                .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
