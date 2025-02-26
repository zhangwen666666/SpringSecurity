package com.zw.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// OncePerRequestFilter是spring框架提供的一个抽象类
// 该抽象类实现了Filter接口
@Component
public class CaptchaFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 如果不是登录请求，则不需要判断验证码
        String requestURI = request.getRequestURI(); // "/user/login"
        if ("/user/login".equals(requestURI)) {
            // 是登录请求，验证验证码
            // 接收前端的验证码
            String code = request.getParameter("captcha"); // 参数名对应login.html的验证码表单项
            // 生成的验证码存储在session域中，获取session域中的验证码
            String sessionCode = (String) request.getSession().getAttribute("captcha");
            if (!StringUtils.hasText(code)) {
                // 验证码为空，验证未通过
                response.sendRedirect("/");
            } else if (!code.equalsIgnoreCase(sessionCode)) {
                // 如果不相等，验证没通过
                response.sendRedirect("/");
            } else {
                // 继续向下执行其他过滤
                filterChain.doFilter(request, response);
            }
        } else {
            // 不是登录请求，直接放行
            filterChain.doFilter(request, response);
        }
    }
}
