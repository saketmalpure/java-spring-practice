package com.sample;

import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${s3.accessKey:}")
    private String s3AccessKey;

    @Value("${s3.accessSecret:}")
    private String s3AccessSecret;

    @Bean
    public SparkSession spark() throws Exception {

        SparkSession spark = SparkSession
                .builder()
                .appName("Java Spark SQL")
                .config("spark.some.config.option", "some-value")
                .config("spark.master", "local")
                .config("spark.driver.memory ", "5g")
                .config("fs.s3a.impl", "org.apache.hadoop.fs.s3native.NativeS3FileSystem")
                .config("fs.s3a.awsAccessKeyId", s3AccessKey)
                .config("fs.s3a.awsSecretAccessKey", s3AccessSecret)
                .getOrCreate();

        return spark;
    }

}
