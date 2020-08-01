package com.spring.security.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author luyanan
 * @since 2020/7/30
 * <p>令牌配置</p>
 **/
@Configuration
public class TokenConfig {
    private String SIGNING_KEY = "uaa123";

    @Bean
    public TokenStore tokenStore() {
        // 使用内存存储令牌
        return new JwtTokenStore(accessTokenConverter());
    }


    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //对称密钥,资源服务器使用改密钥进行验证
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }

}
