package com.sample.lambdaspring;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


import java.util.Collections;



@Configuration
@ComponentScan
@PropertySource(
        value = { "classpath:application.properties",
                "classpath:application-${ENV:dev}.properties"}, ignoreResourceNotFound = true)
public class AppConfig {

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

}
