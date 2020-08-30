package com.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private Analytics analytics;

    @Scheduled(cron = "${cron}")
    public void calculateAvgBingQueries() throws Exception {
        logger.info("Task scheduler started");
        analytics.readS3File();
        logger.info("Task scheduler stopped");
    }

}
