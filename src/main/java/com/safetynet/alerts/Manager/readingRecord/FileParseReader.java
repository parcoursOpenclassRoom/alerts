package com.safetynet.alerts.Manager.readingRecord;

import org.json.simple.JSONObject;


public interface FileParseReader {
    JSONObject getData(String path);
}
