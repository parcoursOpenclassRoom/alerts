package com.safetynet.alerts.Manager.medicalRecord;

import com.safetynet.alerts.Entity.MedicalRecord;
import com.safetynet.alerts.Entity.Person;
import com.safetynet.alerts.Exception.NotFoundException;
import com.safetynet.alerts.Manager.person.PersonManager;
import com.safetynet.alerts.Repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordManagerImpl implements MedicalRecordManager {
    @Autowired
    MedicalRecordRepository recordRepository;
    @Autowired
    PersonManager personManager;
    @Override
    public MedicalRecord save(MedicalRecord medicalRecord) {
        return recordRepository.save(medicalRecord);
    }

    @Override
    public void delete(String firstName, String lastName) {
        Person person = personManager.findByLastNameAndFirstName(firstName, lastName);
        if(person == null || person.getMedicalRecord() == null)
            new NotFoundException("Not found firestation medicalRecord firstName "+firstName);
        recordRepository.delete(person.getMedicalRecord());
    }
}
