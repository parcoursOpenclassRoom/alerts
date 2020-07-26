package com.safetynet.alerts.integrations;

import com.safetynet.alerts.Manager.readingRecord.ReadingRecordManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ReadingRecordITTest {

    @Autowired
    ReadingRecordManager readingRecordManager;

    final String path = "https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json";

    @Test
    public void readAndSaveTest(){
        readingRecordManager.readAndSave(path);
    }
}

