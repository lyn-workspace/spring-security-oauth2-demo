package com.spring.security.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author luyanan
 * @since 2020/7/27
 * <p></p>
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {

    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;
}
