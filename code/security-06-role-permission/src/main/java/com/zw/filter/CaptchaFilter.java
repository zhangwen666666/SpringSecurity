package com.zw.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CaptchaFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().equals("/login")){
            // 是登录请求，判断验证码
            String code = (String) request.getSession().getAttribute("captcha");
            String captcha = request.getParameter("captcha");
            if (!StringUtils.hasText(captcha) || !code.equalsIgnoreCase(captcha)){
                response.sendRedirect("/");
            }else {
                filterChain.doFilter(request,response);
            }
        }else {
            filterChain.doFilter(request,response);
        }
    }
}
