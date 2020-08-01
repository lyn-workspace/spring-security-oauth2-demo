package com.spring.security.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

/**
 * @author luyanan
 * @since 2020/8/1
 * <p>用户</p>
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    private String id;

    private String username;

    private String password;

    private String mobile;
}
