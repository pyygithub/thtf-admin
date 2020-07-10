package com.thtf.common.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * ---------------------------
 * Web 安全访问控制
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020/1/21 15:21
 * 版本：  v1.0
 * ---------------------------
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * security的鉴权排除的url列表
     */
    private static final String[] EXCLUDED_AUTH_PAGES = {
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/*/v2/api-docs",
            "/v2/api-docs",
            "/api/socket/**",
            "/log",
            "/actuator/**",
            "/*/api-docs",
            "/css/**", "/js/**", "/images/**", "/webjars/**", "**/favicon.ico",
            "/*.html", "/**/*.html", "/**/*.css", "/**/*.js"
    };

    //安全拦截机制（最重要）
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(EXCLUDED_AUTH_PAGES).permitAll()
                .anyRequest()
                .authenticated();
    }
}
