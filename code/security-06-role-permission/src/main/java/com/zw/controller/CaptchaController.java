package com.zw.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ICaptcha;
import cn.hutool.captcha.generator.CodeGenerator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Random;

@Controller
public class CaptchaController {
    @RequestMapping("/common/captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        ICaptcha iCaptcha = CaptchaUtil.createCircleCaptcha(100, 30, new CodeGenerator() {
            @Override
            public String generate() {
                return String.valueOf(new Random().nextInt(9000) + 1000);
            }

            @Override
            public boolean verify(String s, String s1) {
                return false;
            }
        }, 1);

        request.getSession().setAttribute("captcha", iCaptcha.getCode());
        iCaptcha.write(response.getOutputStream());
    }
}
