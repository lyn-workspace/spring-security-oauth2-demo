package com.spring.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author luyanan
 * @since 2020/7/22
 * <p>登陆的接口</p>
 **/
@Controller
public class LoginController {


    @GetMapping("/login-html")
    public String login() {

        return "login";
    }

    @ResponseBody
    @GetMapping("/r/r1")
    public String r1() {
        return getUserName() + ":访问r1的资源";
    }

    @ResponseBody
    @GetMapping("/r/r2")
    public String r2() {
        return getUserName() + ":访问r2的资源";
    }

    @ResponseBody
    @RequestMapping(value = "login‐success")
    public String loginSuccess() {
        return getUserName() + ":登陆成功";
    }


    /**
     * <p>获取用户名</p>
     *
     * @return {@link String}
     * @author luyanan
     * @since 2020/7/27
     */
    public String getUserName() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        String userName = null;
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
