package com.zw.controller;

import com.zw.util.LoginInfoUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

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

    @RequestMapping("/welcome")
    @ResponseBody
    public Object welcome(Principal principal){
        return principal;
    }

    @RequestMapping("/welcome2")
    @ResponseBody
    public Object welcome2(Authentication authentication){
        return authentication;
    }

    @RequestMapping("/welcome3")
    @ResponseBody
    public Object welcome3(UsernamePasswordAuthenticationToken token){
        return token;
    }

    @RequestMapping("/welcome4")
    @ResponseBody
    public Object welcome4(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @RequestMapping("/welcome5")
    @ResponseBody
    public Object welcome5(){
        return LoginInfoUtil.getCurrentLoginUser();
    }
}
