package com.safetynet.alerts.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.safetynet.alerts.Entity.Address;
import com.safetynet.alerts.Entity.Person;
import com.safetynet.alerts.Manager.person.PersonActionManager;
import com.safetynet.alerts.Manager.person.PersonManager;
import com.safetynet.alerts.Manager.util.JsonViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PersonActionController {
    @Autowired
    PersonActionManager personActionManager;

    @JsonView(JsonViews.ViewPersonAddress.class)
    @GetMapping("childAlert")
    public List getChildFromAddress(@RequestParam String address){
        return personActionManager.personChildByAddress(address);
    }

    @JsonView(JsonViews.ViewPersonPhone.class)
    @GetMapping("phoneAlert")
    public List getPhoneFromFirestation(@RequestParam int firestation){
        return personActionManager.personPhoneByFirestation(firestation);
    }

    @JsonView(JsonViews.ViewPersonAddressFire.class)
    @GetMapping("fire")
    public List getPersonsFire(@RequestParam String address){
        return personActionManager.personByAddressAndFire(address);
    }

    @JsonView(JsonViews.ViewPersonStations.class)
    @GetMapping("flood/stations")
    public List<Address> getPersonsStations(@RequestParam int stations){
        return personActionManager.personByStation(stations);
    }

    @JsonView(JsonViews.ViewPersonInfo.class)
    @GetMapping("personInfo")
    public List<Person> getPersonInfo(@RequestParam String firstName, @RequestParam String lastName){
        return personActionManager.getPersonInfo(firstName, lastName);
    }

    @JsonView(JsonViews.ViewPersonEmail.class)
    @GetMapping("communityEmail")
    public List<Person> getEmailFromCity(@RequestParam String city){
        return personActionManager.getEmailFromCity(city);
    }

    @JsonView(JsonViews.ViewFirestation.class)
    @GetMapping("/firestation")
    public Map getPersonsOfStation(@RequestParam int stationNumber){
        return personActionManager.getPersonsOfStation(stationNumber);
    }
}
