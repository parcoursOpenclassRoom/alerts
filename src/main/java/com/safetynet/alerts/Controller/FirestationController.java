package com.safetynet.alerts.Controller;

import com.safetynet.alerts.Manager.firestation.FirestationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FirestationController {

    @Autowired
    FirestationManager firestationManager;

    @GetMapping("/firestation")
    public Map getPersonsOfStation(@RequestParam int stationNumber){
        return firestationManager.getPersonsOfStation(stationNumber);
    }
}
