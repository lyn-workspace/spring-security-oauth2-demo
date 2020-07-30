package com.spring.security.uaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @author luyanan
 * @since 2020/7/30
 * <p>令牌配置</p>
 **/
@Configuration
public class TokenConfig {

    @Bean
    public TokenStore tokenStore() {
        // 使用内存存储令牌
        return new InMemoryTokenStore();
    }

}
