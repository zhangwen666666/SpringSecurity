package com.zw.util;

import com.zw.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserInfoUtil {
    public static User getCurrentUser(){
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
