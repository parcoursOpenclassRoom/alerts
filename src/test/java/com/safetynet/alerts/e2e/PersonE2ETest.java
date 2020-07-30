package com.safetynet.alerts.e2e;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.safetynet.alerts.Entity.Address;
import com.safetynet.alerts.Entity.City;
import com.safetynet.alerts.Entity.Person;
import com.safetynet.alerts.Manager.person.PersonManager;
import com.safetynet.alerts.util.PersonSerializer;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
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
public class PersonE2ETest {
    @Autowired
    PersonManager personManager;
    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;
    String uri = "person";

    @Test
    @Order(1)
    public void createTest() throws Exception {
        Person person = new Person();
        Address address = new Address("341 rue d'estienne");
        person.setFirstName("Aristide");
        person.setLastName("BATIONO");
        person.setPhone("08874697");
        person.setEmail("aristide.laurent@gmail.com");
        address.setCity(new City("Colombes", "92700"));
        person.setAddress(address);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(person);

        MvcResult result = mockMvc.perform(post(createURLWithPort(uri)).contentType(APPLICATION_JSON)
                .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);
        assertNotNull(jsonObject);
        assertEquals(person.getFirstName(), jsonObject.get("firstName"));
    }

    @Test
    @Order(2)
    public void updateTest() throws Exception {
        Person person = personManager.findByLastNameAndFirstName("Aristide", "BATIONO");
        person.setEmail("aristide@gmail.com");
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule("PersonSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(Person.class, new PersonSerializer());
        mapper.registerModule(module);
        String requestJson=mapper.writeValueAsString(person);
        MvcResult result = mockMvc.perform(put(createURLWithPort(uri)).contentType(APPLICATION_JSON)
                .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);
        assertNotNull(jsonObject);
        assertEquals(person.getEmail(), jsonObject.get("email"));
        assertEquals(person.getId(), jsonObject.get("id"));
    }

    @Test
    @Order(3)
    public void deleteTest() throws Exception {
        mockMvc.perform(delete(createURLWithPort(uri+"/Aristide/BATIONO")).contentType(APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
