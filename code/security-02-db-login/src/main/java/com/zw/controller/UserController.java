package com.zw.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @RequestMapping("/")
    public String index(){
        return "Welcome to Spring Security";
    }
}
