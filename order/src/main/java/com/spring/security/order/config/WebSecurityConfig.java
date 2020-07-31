package com.spring.security.order.config;

        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author luyanan
 * @since 2020/7/31
 * <p>安全访问控制</p>
 **/
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    //安全拦截机制
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/r/**").authenticated()// 所有r/**的请求都必须通过认证
                .anyRequest().permitAll(); // 除了r/r** 之外的请求都放行
    }
}
