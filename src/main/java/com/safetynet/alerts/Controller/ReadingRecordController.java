package com.safetynet.alerts.Controller;

import com.safetynet.alerts.Manager.readingRecord.ReadingRecordManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ReadingRecordController {

    @Autowired
    ReadingRecordManager readingRecordManager;

    @GetMapping("/reading-url")
    public Map getReadingFromUrl(@RequestParam String url){
        return readingRecordManager.readAndSave(url);
    }
}
