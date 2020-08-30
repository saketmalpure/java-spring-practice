package com.sample;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Analytics {

    private static final Logger logger = LoggerFactory.getLogger(Analytics.class);

    @Value("${s3.bucketName}")
    private String s3BucketName;

    @Value("${s3.filePath}")
    private String s3Path;

    @Value("${s3.protocol}")
    private String s3Protocol;

    @Autowired
    SparkSession spark;

    public void readS3File() throws Exception {

        try {
            String s3FilePath = s3Protocol + s3BucketName + "/" + s3Path;
            Dataset<Row> report = spark.read().json(s3FilePath);
            System.out.println(report.count());
            spark.stop();
        } catch (Exception e) {
            logger.error("Error : " + ExceptionUtils.getStackTrace(e));
        }

    }

}
