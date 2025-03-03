package com.zw.controller;

import com.zw.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @GetMapping("/")
    @ResponseBody
    public String index(){
        return "welcome index";
    }

    @GetMapping("/welcome")
    @ResponseBody
    public User welcome(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
