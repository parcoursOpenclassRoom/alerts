package com.safetynet.alerts.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.safetynet.alerts.Entity.Address;
import com.safetynet.alerts.Manager.person.PersonManager;
import com.safetynet.alerts.Manager.util.JsonViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    PersonManager personManager;

    @JsonView(JsonViews.ViewPersonAddress.class)
    @GetMapping("childAlert")
    public List getChildFromAddress(@RequestParam String address){
        return personManager.personChildByAddress(address);
    }

    @JsonView(JsonViews.ViewPersonPhone.class)
    @GetMapping("phoneAlert")
    public List getPhoneFromFirestation(@RequestParam int firestation){
        return personManager.personPhoneByFirestation(firestation);
    }

    @JsonView(JsonViews.ViewPersonAddressFire.class)
    @GetMapping("fire")
    public List getPersonsFire(@RequestParam String address){
        return personManager.personByAddressAndFire(address);
    }

    @JsonView(JsonViews.ViewPersonStations.class)
    @GetMapping("flood/stations")
    public List<Address> getPersonsStations(@RequestParam int stations){
        return personManager.personByStation(stations);
    }
}
