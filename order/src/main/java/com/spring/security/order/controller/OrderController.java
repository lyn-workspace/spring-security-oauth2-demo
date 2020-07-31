package com.spring.security.order.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luyanan
 * @since 2020/7/31
 * <p>测试控制层</p>
 **/
@RestController
@RequestMapping("r")
public class OrderController {


    @GetMapping("r1")
    @PreAuthorize("hasAnyAuthority('p1')")
    public String r1() {
        return "访问r1资源";
    }

}
