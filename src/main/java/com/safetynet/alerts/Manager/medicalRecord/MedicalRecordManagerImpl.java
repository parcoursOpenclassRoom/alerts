package com.safetynet.alerts.Manager.medicalRecord;

import com.safetynet.alerts.Entity.MedicalRecord;
import com.safetynet.alerts.Repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordManagerImpl implements MedicalRecordManager {
    @Autowired
    MedicalRecordRepository recordRepository;
    @Override
    public MedicalRecord save(MedicalRecord medicalRecord) {
        return recordRepository.save(medicalRecord);
    }
}
