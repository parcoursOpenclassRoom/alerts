package com.safetynet.alerts.Controller;

import com.safetynet.alerts.Entity.MedicalRecord;
import com.safetynet.alerts.Manager.medicalRecord.MedicalRecordManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {
    @Autowired
    MedicalRecordManager medicalRecordManager;

    @PostMapping
    public MedicalRecord create(@RequestBody MedicalRecord medicalRecord){
        return medicalRecordManager.save(medicalRecord);
    }

    @PutMapping
    public MedicalRecord update(@RequestBody MedicalRecord medicalRecord){
        return medicalRecordManager.save(medicalRecord);
    }

    @DeleteMapping("/{firstName}/{lastName}")
    public void delete(@PathVariable String firstName, String lastName){
        medicalRecordManager.delete(firstName, lastName);
    }
}
