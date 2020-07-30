package com.safetynet.alerts;

import com.safetynet.alerts.Manager.readingRecord.ReadingRecordManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AlertsApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AlertsApplication.class, args);

        context.getBean(ReadingRecordManager.class).readAndSave("https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json"); // <-- here
    }

}
