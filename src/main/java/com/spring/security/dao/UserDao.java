package com.spring.security.dao;

import com.spring.security.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author luyanan
 * @since 2020/7/27
 * <p></p>
 **/
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * <p>根据用户名查询用户信息</p>
     *
     * @param userName 用户名
     * @return {@link UserEntity}
     * @author luyanan
     * @since 2020/7/27
     */
    public UserEntity findByUserName(String userName) {

        String sql = "select id,username,password,fullname,mobile from t_user where username = ?";
        List<UserEntity> query = jdbcTemplate.query(sql, new Object[]{userName}, new BeanPropertyRowMapper(UserEntity.class));
        return CollectionUtils.isEmpty(query) ? null : query.get(0);
    }

}
