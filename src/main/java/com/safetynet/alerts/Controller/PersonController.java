package com.safetynet.alerts.Controller;

import com.fasterxml.jackson.annotation.JsonView;
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
}