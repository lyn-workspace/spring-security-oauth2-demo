package com.spring.security.order.utils;

import com.alibaba.fastjson.JSON;
import com.spring.security.order.entity.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author luyanan
 * @since 2020/9/4
 * <p>安全工具类</p>
 **/
public class SecurityUtils {

    public static UserEntity getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserEntity) {
            return (UserEntity) principal;
        }
        return JSON.parseObject(principal.toString(), UserEntity.class);
    }

}
