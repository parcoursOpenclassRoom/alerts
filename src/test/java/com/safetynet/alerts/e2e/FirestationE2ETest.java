package com.safetynet.alerts.e2e;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.safetynet.alerts.Entity.Address;
import com.safetynet.alerts.Entity.City;
import com.safetynet.alerts.Entity.Firestation;
import com.safetynet.alerts.Entity.Person;
import com.safetynet.alerts.Manager.address.AddressManager;
import com.safetynet.alerts.Manager.firestation.FirestationManager;
import com.safetynet.alerts.util.FirestationSerializer;
import com.safetynet.alerts.util.PersonSerializer;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FirestationE2ETest {
    @Autowired
    FirestationManager firestationManager;
    @Autowired
    AddressManager addressManager;
    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;
    String uri = "firestation";

    @Test
    @Order(1)
    public void createTest() throws Exception {
        Firestation firestation = new Firestation();
        Address address = new Address("341 rue destienne");
        address.setCity(new City("Colombes", "92700"));
        firestation.setAddress(address);
        firestation.setStation("2");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(firestation);
        MvcResult result = mockMvc.perform(post(createURLWithPort(uri)).contentType(APPLICATION_JSON)
                .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);
        assertNotNull(jsonObject);
        assertEquals(firestation.getStation(), jsonObject.get("station"));
    }

    @Test
    @Order(2)
    public void updateTest() throws Exception {
        Address address = addressManager.findByLibelle("341 rue destienne");
        Firestation firestation = firestationManager.findByAddressAndStation(address, "2");
        firestation.setStation("3");
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule("PersonSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(Firestation.class, new FirestationSerializer());
        mapper.registerModule(module);
        String requestJson=mapper.writeValueAsString(firestation);
        System.out.println(requestJson);

        MvcResult result = mockMvc.perform(put(createURLWithPort(uri)).contentType(APPLICATION_JSON)
                .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);
        assertNotNull(jsonObject);
        assertEquals(firestation.getStation(), jsonObject.get("station"));
        assertEquals(firestation.getId(), jsonObject.get("id"));
    }

    @Test
    @Order(3)
    public void deleteTest() throws Exception {
        int firestationId = 2;
        mockMvc.perform(delete(createURLWithPort(uri+"/"+firestationId)).contentType(APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
