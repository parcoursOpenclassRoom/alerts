package com.safetynet.alerts.e2e;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
public class PersonActionE2ETest {

    @LocalServerPort
    private int port;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void readAndSaveJsonTest() throws Exception {
        String uri = "api/reading-url?url=https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json";
        mockMvc.perform(get(createURLWithPort(uri)).contentType(APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void getPersonsOfStationTest() throws Exception {
        String uri = "firestation?stationNumber=1";
        mockMvc.perform(get(createURLWithPort(uri)).contentType(APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    public void getChildFromAddressTest() throws Exception {
        String uri = "childAlert?address=1509 Culver St";
        mockMvc.perform(get(createURLWithPort(uri)).contentType(APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void getPhoneFromFirestationTest() throws Exception {
        String uri = "phoneAlert?firestation=1";
        mockMvc.perform(get(createURLWithPort(uri)).contentType(APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void getPersonsFireTest() throws Exception {
        String uri = "fire?address=1509 Culver St";
        mockMvc.perform(get(createURLWithPort(uri)).contentType(APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(6)
    public void getPersonsStationsTest() throws Exception {
        String uri = "flood/stations?stations=12";
        mockMvc.perform(get(createURLWithPort(uri)).contentType(APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(7)
    public void getEmailFromCityTest() throws Exception {
        String uri = "communityEmail?city=Culver";
        mockMvc.perform(get(createURLWithPort(uri)).contentType(APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
