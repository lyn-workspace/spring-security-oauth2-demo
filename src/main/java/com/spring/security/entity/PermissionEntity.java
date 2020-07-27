package com.spring.security.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author luyanan
 * @since 2020/7/27
 * <p>权限</p>
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionEntity {

    private String id;
    private String code;
    private String description;
    private String url;

}
