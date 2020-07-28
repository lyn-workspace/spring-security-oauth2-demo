package com.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author luyanan
 * @since 2020/7/22
 * <p>安全配置</p>
 **/

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    // 配置用户信息
//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("zhangsan").password("$2a$10$Z0lpyLYr3DYWdpRQPr3i5eVx3Q1LolIz7QUDCcq5aFePUh5IahaEa").authorities("p1").build());
//        manager.createUser(User.withUsername("lisi").password("$2a$10$Z0lpyLYr3DYWdpRQPr3i5eVx3Q1LolIz7QUDCcq5aFePUh5IahaEa").authorities("p2").build());
//        return manager;
//    }


//    // 密码编码器
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // 配置安全拦截机制
    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http
                .csrf().disable() // //屏蔽CSRF控制，即spring security不再限制CSRF


                .authorizeRequests()

                //访问/r/r1资源的 url需要拥有p1权限。
                .antMatchers("/r/r1").hasAuthority("p1")
                //访问/r/r2资源的 url需要拥有p2权限。
                .antMatchers("/r/r2").hasAuthority("p2")
                .antMatchers("/r/r3").access("hasAuthority('p1') and hasAuthority('p2')")
                .antMatchers("/r/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()  // (1)
                .loginPage("/login-html")  // (2)
                .loginProcessingUrl("/login")  // (3)
                .successForwardUrl("/login‐success") // (4)
                .permitAll()
                .and()
                .logout()    // (1)
                .logoutSuccessUrl("/login-html?logout") // (2)
                .addLogoutHandler(new LogoutHandler() {// (3)
                    @Override
                    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {

                    }
                })
                .logoutUrl("/logout")  // (4)
                .addLogoutHandler(new LogoutHandler() { // (5)
                    @Override
                    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {

                    }
                }).invalidateHttpSession(true); // (6)
    }
}
