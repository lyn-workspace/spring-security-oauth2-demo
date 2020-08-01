package com.spring.security.uaa.config;

import com.alibaba.fastjson.JSON;
import com.spring.security.uaa.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author luyanan
 * @since 2020/8/1
 * <p></p>
 **/
@Service
public class ConsumerUserDetailsService implements UserDetailsService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity zhangsan = UserEntity.builder().id("1").username("zhangsan").password(passwordEncoder.encode("123")).mobile("18111111111").build();

        return User.withUsername(JSON.toJSONString(zhangsan)).password(zhangsan.getPassword()).authorities("p1").build();

    }
}
