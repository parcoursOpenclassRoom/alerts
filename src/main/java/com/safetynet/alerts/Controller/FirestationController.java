package com.safetynet.alerts.Controller;

import com.safetynet.alerts.Manager.firestation.FirestationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FirestationController {

    @Autowired
    FirestationManager firestationManager;


}
