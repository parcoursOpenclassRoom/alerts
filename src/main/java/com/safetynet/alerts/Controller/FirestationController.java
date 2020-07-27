package com.safetynet.alerts.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.safetynet.alerts.Manager.firestation.FirestationManager;
import com.safetynet.alerts.Manager.util.JsonViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FirestationController {

    @Autowired
    FirestationManager firestationManager;

    @JsonView(JsonViews.ViewFirestation.class)
    @GetMapping("/firestation")
    public Map getPersonsOfStation(@RequestParam int stationNumber){
        return firestationManager.getPersonsOfStation(stationNumber);
    }
}
