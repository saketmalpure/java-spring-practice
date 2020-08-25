package com.sample.lambdaspring;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
/**
 *
 */
@Service
public class ProxyService
{
    @Value("${API_SERVER}")
    private String apiServer;

    public void helloWorld(String message)
    {
        System.out.println(message);
    }
}
