package com.spring.security.uaa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author luyanan
 * @since 2020/9/3
 * <p></p>
 **/
@Controller
public class AuthController
{

    @GetMapping(path = "/")
    public String index() {
        return "index";
    }


    @GetMapping(path = "/login")
    public String login() {
        return "login";
    }

}
