package com.thtf.flowable;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.thtf.flowable.mapper")
@ComponentScan(basePackages = {"com.thtf"})
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class FlowableApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableApplication.class, args);
    }
}
