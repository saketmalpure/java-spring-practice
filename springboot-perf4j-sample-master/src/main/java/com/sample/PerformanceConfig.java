package com.sample;

import org.perf4j.slf4j.aop.TimingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by saket on 26/4/18.
 */
@Configuration
@EnableAspectJAutoProxy
public class PerformanceConfig {
    @Bean
    public TimingAspect timingAspect() {
        return new TimingAspect();
    }
}
