package com.zw.handler;

import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWTUtil;
import com.zw.constant.Constant;
import com.zw.entity.User;
import com.zw.result.R;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 登录成功会执行该Handler
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 生成JWT
        User user = (User) authentication.getPrincipal();
        String userJson = JSONUtil.toJsonStr(user);
        String token = JWTUtil.createToken(Map.of("user", userJson), Constant.secret.getBytes());

        // 写入redis (大键，<用户id，token>)
        redisTemplate.opsForHash().put(Constant.REDIS_TOKEN_KEY, user.getId().toString(), token);

        response.setContentType("application/json;charset=UTF-8");
        // 封装返回数据
        R<String> result = R.OK(token);
        String jsonStr = JSONUtil.toJsonStr(result);
        // 把json相应给客户端
        response.getWriter().write(jsonStr);
    }
}
