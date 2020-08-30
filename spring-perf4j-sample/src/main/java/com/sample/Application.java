package com.sample;

import org.perf4j.StopWatch;
import org.perf4j.aop.Profiled;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by saket on 26/4/18.
 */

@SpringBootApplication
public class Application implements CommandLineRunner{

    @Autowired
    private Sample sample;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.print(sample.getName());
        Thread.sleep(60000);
    }
    
}
