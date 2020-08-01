package com.spring.security.order.controller;

import com.spring.security.order.entity.UserEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luyanan
 * @since 2020/7/31
 * <p>测试控制层</p>
 **/
@RestController
public class OrderController {


    @GetMapping("r1")
    @PreAuthorize("hasAnyAuthority('p1')")
    public String r1() {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userEntity.getId() + ":访问r1资源";
    }

    @GetMapping("r2")
    @PreAuthorize("hasAnyAuthority('p2')")
    public String r2() {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userEntity.getUsername() + ":访问r2资源";
    }
}
