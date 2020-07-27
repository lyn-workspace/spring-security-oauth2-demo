package com.spring.security.config;

import com.spring.security.dao.UserDao;
import com.spring.security.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luyanan
 * @since 2020/7/24
 * <p>自定义UserDetailsService</p>
 **/
@Service
public class ConsumerUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        // 这里使用静态数据
//        return User.withUsername("zhangsan").password("$2a$10$Z0lpyLYr3DYWdpRQPr3i5eVx3Q1LolIz7QUDCcq5aFePUh5IahaEa").authorities("p1").build();
        UserEntity userEntity =
                userDao.findByUserName(s);
        if (null == userEntity) {
            return null;
        }
        List<String> permission = userDao.findPermissionByUserId(userEntity.getId());

        UserDetails userDetails =
                User
                        .withUsername(userEntity.getFullname())
                        .password(userEntity.getPassword())
                        .authorities(permission.toArray(new String[permission.size()]))
                        .build();
        return userDetails;
    }

}
