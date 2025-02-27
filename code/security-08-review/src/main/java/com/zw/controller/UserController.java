package com.zw.controller;

import com.zw.entity.User;
import com.zw.util.UserInfoUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @GetMapping("/")
    @ResponseBody
    public String index(){
        return "hello!!!";
    }

    @PostMapping("/welcome")
    @ResponseBody
    public User welcome(){
        return UserInfoUtil.getCurrentUser();
    }
}
