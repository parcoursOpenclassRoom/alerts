package com.safetynet.alerts.Controller;

import com.safetynet.alerts.Entity.Firestation;
import com.safetynet.alerts.Manager.firestation.FirestationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/firestation")
public class FirestationController {
    Logger logger = LogManager.getLogger(FirestationController.class);

    @Autowired
    FirestationManager firestationManager;

    @PostMapping
    public Firestation create(@RequestBody Firestation firestation){
        logger.info("App initialized!!!");
        return firestationManager.save(firestation);
    }

    @PutMapping
    public Firestation update(@RequestBody Firestation firestation){
        return firestationManager.save(firestation);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        firestationManager.delete(id);
    }
}
