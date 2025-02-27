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
        String requestURI = request.getRequestURI();
        if ("/login".equals(requestURI)) {
            String formCaptcha = request.getParameter("captcha");
            String realCaptcha = (String) request.getSession().getAttribute("captcha");
            if (!StringUtils.hasText(formCaptcha) || !formCaptcha.equalsIgnoreCase(realCaptcha)) {
                response.sendRedirect("/");
            } else {
                filterChain.doFilter(request, response);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
