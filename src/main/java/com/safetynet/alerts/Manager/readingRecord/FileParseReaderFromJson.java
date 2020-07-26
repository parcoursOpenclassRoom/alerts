package com.safetynet.alerts.Manager.readingRecord;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

@Service
public class FileParseReaderFromJson implements FileParseReader {
    @Override
    public JSONObject getData(String url)  {
        try {
            // get open connection
            URL urlService = new URL(url.replaceAll(" ", "+"));
            URLConnection yc = urlService.openConnection();
            // read initialization
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            String jsonString = "";
            // each line and stored in var jsonString
            while ((inputLine = in.readLine()) != null) {
                jsonString += inputLine;
            }
            in.close();
            // parse string to json
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(jsonString);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
