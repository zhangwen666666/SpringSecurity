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
    @RequestMapping(value = "/common/captcha")
    public void generateCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 生成的是一个验证码的图片,
        // 不需要跳转页面，就是把生成的图片以IO流的方式写出去

        // 1.生成验证码图片
        // 方法原型：createCircleCaptcha(图片宽度, 图片高度, 代码个数, 干扰线个数, 字体大小-高度的倍数)
        // ICaptcha iCaptcha = CaptchaUtil.createCircleCaptcha(100, 30, 4, 10, 1); // 验证码包含英文和数字
        // 原型2： createCircleCaptcha(int width, int height, CodeGenerator generator, int circleCount)
        // CodeGenerator验证码生成器 可以自定义验证码生成规则
        ICaptcha iCaptcha = CaptchaUtil.createCircleCaptcha(100, 30, new CodeGenerator() {
            @Override
            public String generate() {
                return String.valueOf(1000 + new Random().nextInt(9000));
            }

            @Override
            public boolean verify(String s, String s1) {
                return false;
            }
        }, 1);

        // 2. 把图片中的验证码字符串（有几个数字）在后端保存起来，前后端不分离项目保存到Session中
        // 后续前端提交过来，后端需要验证提交的验证码对不对
        request.getSession().setAttribute("captcha", iCaptcha.getCode());

        // 3. 把生成的验证码图片以IO流的形式写出去
        response.setContentType("image/jpeg"); // 告诉浏览器响应的内容类型是jpeg格式
        iCaptcha.write(response.getOutputStream());
    }
}
