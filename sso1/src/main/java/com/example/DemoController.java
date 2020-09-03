package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author luyanan
 * @since 2020/9/3
 * <p></p>
 **/
@Controller
public class DemoController {

    @GetMapping(path = "/")
    public String index() {
        return "index";
    }


    @GetMapping(path = "/securedPage")
    public String login() {
        return "securedPage";
    }



    @GetMapping("ping")
    @ResponseBody
    public Object test() {
        return "pong";
    }

    @GetMapping("noauth")
    public Object noauth() {
        return "noauth";
    }

}
