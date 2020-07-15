package com.thtf.flowable.config;


import lombok.extern.slf4j.Slf4j;
import org.flowable.ui.modeler.properties.FlowableModelerAppProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class FlowableProcessEngineConfig {

    @Bean
    public FlowableModelerAppProperties flowableModelerAppProperties() {
        return new FlowableModelerAppProperties();
    }

}
