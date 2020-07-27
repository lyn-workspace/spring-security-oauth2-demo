package com.spring.security.dao;

import com.spring.security.entity.PermissionEntity;
import com.spring.security.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * <p>根据用户id查询权限列表</p>
     *
     * @param userId
     * @return {@link List< String>}
     * @author luyanan
     * @since 2020/7/27
     */
    public List<String> findPermissionByUserId(String userId) {

        String sql = "SELECT * FROM t_permission WHERE id IN(\n" +
                "SELECT permission_id FROM t_role_permission WHERE role_id IN(\n" +
                "\tSELECT role_id FROM t_user_role WHERE user_id = ? \n" +
                ")\n" +
                ")";
        List<PermissionEntity> query = jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(PermissionEntity.class));
        return query.stream().map(PermissionEntity::getCode).collect(Collectors.toList());

    }

}
