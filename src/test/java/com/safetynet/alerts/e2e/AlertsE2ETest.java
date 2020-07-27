package com.safetynet.alerts.e2e;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AlertsE2ETest {

    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    int STATUS_CODE_SUCCESS = 200;

    @Test
    public void readAndSaveJsonTest(){
        String uri = "api/reading-url?url=https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json";
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Map> response = restTemplate.exchange(
                createURLWithPort(uri),
                HttpMethod.GET, entity, Map.class);
        assertEquals(STATUS_CODE_SUCCESS , response.getStatusCodeValue());
    }

    @Test
    public void getPersonsOfStationTest(){
        String uri = "firestation?stationNumber=1";
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Map> response = restTemplate.exchange(
                createURLWithPort(uri),
                HttpMethod.GET, entity, Map.class);
        assertEquals(STATUS_CODE_SUCCESS , response.getStatusCodeValue());
    }

    @Test
    public void getChildFromAddressTest(){
        String uri = "childAlert?address=1509 Culver St";
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<List> response = restTemplate.exchange(
                createURLWithPort(uri),
                HttpMethod.GET, entity, List.class);
        assertEquals(STATUS_CODE_SUCCESS , response.getStatusCodeValue());
    }

    @Test
    public void getPhoneFromFirestationTest(){
        String uri = "phoneAlert?firestation=1";
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<List> response = restTemplate.exchange(
                createURLWithPort(uri),
                HttpMethod.GET, entity, List.class);
        assertEquals(STATUS_CODE_SUCCESS , response.getStatusCodeValue());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
