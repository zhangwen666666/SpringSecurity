package com.zw.controller;

import com.zw.entity.User;
import com.zw.util.UserInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class UserController {
    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "welcome!!!!";
    }

    @GetMapping("/toLogin")
    public String login(){
        return "login";
    }

    @RequestMapping("/welcome")
    @ResponseBody
    public User welcome(){
        return UserInfoUtil.getCurrentUser();
    }
}
