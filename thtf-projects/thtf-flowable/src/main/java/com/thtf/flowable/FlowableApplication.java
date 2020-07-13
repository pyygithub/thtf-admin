package com.thtf.flowable;

import org.flowable.ui.common.conf.DevelopmentConfiguration;
import org.flowable.ui.common.rest.idm.remote.RemoteAccountResource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@ComponentScan(basePackages = {"com.thtf", "org.flowable.ui"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
        classes = {RemoteAccountResource.class, DevelopmentConfiguration.class}))
@SpringBootApplication(exclude = {
        SecurityAutoConfiguration.class
})
public class FlowableApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableApplication.class, args);
    }
}
