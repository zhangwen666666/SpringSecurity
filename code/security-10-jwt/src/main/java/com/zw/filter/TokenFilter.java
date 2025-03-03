package com.zw.filter;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWTUtil;
import com.zw.constant.Constant;
import com.zw.entity.User;
import com.zw.result.R;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 登录接口，不需要验证token，因为此时还没有生成token
        String requestURI = request.getRequestURI();
        if (requestURI.equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        response.setCharacterEncoding("UTF-8");
        String token = request.getHeader("token");
        // token 为空
        if (!StringUtils.hasText(token)) {
            R<Object> result = R.error(901, "请求token为空");
            response.getWriter().write(JSONUtil.toJsonStr(result));
            return;
        }

        boolean verify = false; // 假设验证不通过
        try {
            verify = JWTUtil.verify(token, Constant.secret.getBytes());
        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println("验证发生异常");
        }

        // 验证不通过
        if (!verify) {
            R<Object> result = R.error(902, "请求token非法");
            response.getWriter().write(JSONUtil.toJsonStr(result));
            return;
        }

        // 获取负载数据
        JSONObject payloads = JWTUtil.parseToken(token).getPayloads();
        String userJSON = payloads.get("user", String.class);
        // 将user对象的Json字符串反向转换为user对象
        User user = JSONUtil.toBean(userJSON, User.class);
        Integer userId = user.getId();
        String redisToken = (String) redisTemplate.opsForHash().get(Constant.REDIS_TOKEN_KEY, String.valueOf(userId));

        // 前端token与redis中的token不匹配
        if (!token.equals(redisToken)) {
            R<Object> result = R.error(903, "请求token错误");
            response.getWriter().write(JSONUtil.toJsonStr(result));
            return;
        }

        // token验证通过之后，要在SpringSecurity的上下文中放置一个认证对象
        // 这样，框架在执行后序的Filter的时候，才知道是登录的
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
