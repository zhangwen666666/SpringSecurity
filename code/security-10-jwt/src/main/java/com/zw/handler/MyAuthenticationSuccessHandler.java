package com.zw.handler;

import cn.hutool.json.JSONUtil;
import com.zw.result.R;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 登录成功会执行该Handler
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        // 封装返回数据
        R<Authentication> result = R.OK(authentication);
        String jsonStr = JSONUtil.toJsonStr(result);
        // 把json相应给客户端
        response.getWriter().write(jsonStr);
    }
}
