package com.spring.security.service;

import com.spring.security.entity.UserEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * @author luyanan
 * @since 2020/7/28
 * <p></p>
 **/
public interface IUserService {


//    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
//    UserEntity findById(String id);
//
//    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
//    List<UserEntity> findByPage(int page);
//
//    @Secured("ROLE_TELLER")
//    void save(UserEntity userEntity);




    @PreAuthorize("isAnonymous()")
    UserEntity findById(String id);

    @PreAuthorize("isAnonymous()")
    List<UserEntity> findByPage(int page);

    @PreAuthorize("hasAuthority('findById') and hasAuthority('findByPage')")
    void save(UserEntity userEntity);
}
