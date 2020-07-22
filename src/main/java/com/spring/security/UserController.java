package com.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author luyanan
 * @since 2020/7/22
 * <p>用户的控制层和</p>
 **/
@Controller
public class UserController {


    static Map<String, String> users = new HashMap<>();

    static {

        users.put("zhangsan", "123456");
        users.put("lisi", "234567");
    }


    @Autowired
    private HttpSession httpSession;


    /**
     * <p>登陆页面</p>
     *
     * @return {@link String}
     * @author luyanan
     * @since 2020/7/22
     */
    @GetMapping("login")
    public String index() {
        return "login";
    }

    /**
     * <p>登陆接口</p>
     *
     * @param userName
     * @param passWord
     * @return {@link String}
     * @author luyanan
     * @since 2020/7/22
     */
    @ResponseBody
    @PostMapping("login")
    public String login(String userName, String passWord) {

        Assert.hasText(userName, "用户名为空");
        Assert.hasText(passWord, "密码为空");
        String s = users.get(userName);
        if (null == s) {
            throw new RuntimeException("用户不存在");
        }
        if (!passWord.equals(s)) {
            throw new RuntimeException("密码不正确");
        }
        httpSession.setAttribute("name", userName);
        return userName + ":登陆成功";
    }


    /**
     * <p>个人详情</p>
     *
     * @return {@link String}
     * @author luyanan
     * @since 2020/7/22
     */
    @ResponseBody
    @GetMapping("info")
    public String info() {
        Object name = httpSession.getAttribute("name");
        return Optional.ofNullable(name).orElse("匿名") + ":访问info";
    }


    /**
     * <p>注销</p>
     *
     * @return {@link String}
     * @author luyanan
     * @since 2020/7/22
     */

    @ResponseBody
    @GetMapping("logout")
    public void logout() {
        httpSession.invalidate();
    }
}
