package com.zw.util;

import com.zw.entity.TUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoginInfoUtil {
    public static TUser getCurrentLoginUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TUser tUser = (TUser) authentication.getPrincipal();
        return tUser;
    }
}
