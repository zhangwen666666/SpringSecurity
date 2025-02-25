package com.zw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "Welcome to Spring Security";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
}
