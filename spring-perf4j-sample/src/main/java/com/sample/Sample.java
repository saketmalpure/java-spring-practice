package com.sample;

import org.perf4j.aop.Profiled;
import org.springframework.stereotype.Component;

/**
 * Created by saket on 27/4/18.
 */
@Component
public class Sample {
    
    @Profiled(tag="perfCheck")
    public String getName(){
        return "Check the logs";
    }
    
}
