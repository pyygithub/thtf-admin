package com.thtf.base.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * ---------------------------
 * 通用用管理系统业务实现启动类
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/27 15:38
 * 版本：  v1.0
 * ---------------------------
 */
@MapperScan("com.thtf.base.server.mapper")
@ComponentScan(basePackages = "com.thtf")
@EnableDiscoveryClient
@SpringBootApplication
public class SystemBaseServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemBaseServerApplication.class, args);
    }
}
