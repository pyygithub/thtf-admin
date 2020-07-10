package com.thtf.common.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * ---------------------------
 * Token令牌配置
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020/1/20 11:54
 * 版本：  v1.0
 * ---------------------------
 */
@Configuration
public class TokenConfig {
    private String SIGNING_KEY = "pyy";

    @Bean
    public TokenStore tokenStore() {
        // 使用内存存储令牌（普通令牌）
        return new InMemoryTokenStore();
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY); //对称秘钥，资源服务器使用该秘钥来验证
        return converter;
    }
}
