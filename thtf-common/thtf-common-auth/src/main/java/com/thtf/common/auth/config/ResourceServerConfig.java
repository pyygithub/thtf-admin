package com.thtf.common.auth.config;

import com.thtf.common.auth.handler.CustomAccessDeniedHandler;
import com.thtf.common.auth.handler.CustomAuthExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * ---------------------------
 * 资源服务器配置
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020/1/21 14:36
 * 版本：  v1.0
 * ---------------------------
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    public static final String RESOURCE_ID = "res1"; // 资源ID（和授权服务配置的resourceId保持一致）

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .authenticationEntryPoint(new CustomAuthExceptionEntryPoint())
                .accessDeniedHandler(new CustomAccessDeniedHandler())
                .resourceId(RESOURCE_ID) // 资源ID
                //.tokenServices(tokenService()) // 资源服务令牌解析服务(去掉，自己校验）
                .tokenStore(tokenStore)
                .stateless(true); // 标记以指示在这些资源上仅允许基于令牌的身份验证
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").access("#oauth2.hasScope('all')")
                .and().csrf().disable() // 关闭 csrf
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

//    // 资源服务令牌解析服务
//    @Bean
//    public ResourceServerTokenServices tokenService() {
//        // 使用远程服务请求授权服务器校验token, 必须指定校验token 的url、client_id，client_secret
//        RemoteTokenServices service=new RemoteTokenServices();
//        service.setCheckTokenEndpointUrl("http://localhost:9001/oauth/check_token");
//        service.setClientId("client_abc");
//        service.setClientSecret("secret");
//        return service;
//    }
}
