package com.spring.security.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author luyanan
 * @since 2020/7/24
 * <p>自定义UserDetailsService</p>
 **/
@Service
public class ConsumerUserDetailsService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        // 这里使用静态数据
        return User.withUsername("zhangsan").password("$2a$10$Z0lpyLYr3DYWdpRQPr3i5eVx3Q1LolIz7QUDCcq5aFePUh5IahaEa").authorities("p1").build();
    }
}
