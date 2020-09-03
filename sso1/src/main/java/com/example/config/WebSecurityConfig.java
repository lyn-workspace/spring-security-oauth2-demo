package com.example.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author luyanan
 * @since 2020/7/31
 * <p>安全访问控制</p>
 **/
@Configuration
@EnableWebSecurity(debug = true)
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    //安全拦截机制
    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/**").authenticated()// 所有r/**的请求都必须通过认证
//                .anyRequest().permitAll(); // 除了r/r** 之外的请求都放行
        http.rememberMe()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .antMatchers("/login**").permitAll()
                .antMatchers("/logout**").permitAll()
                .antMatchers("/**").hasAnyRole("ROLE_ADMIN", "ROLE_USER")
                .antMatchers("/auth/**", "/oauth2/**").permitAll()
                .and()
                .formLogin().permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/logout_success")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
                .csrf()
                .disable();
    }
}
