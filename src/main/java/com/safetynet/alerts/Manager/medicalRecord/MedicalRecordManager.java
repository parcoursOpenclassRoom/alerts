package com.safetynet.alerts.Manager.medicalRecord;

import com.safetynet.alerts.Entity.MedicalRecord;

/**
 * MedicalRecord actions manager
 */
public interface MedicalRecordManager {
    /**
     * save the medicalRecord and return instance save
     * @param medicalRecord
     * @return
     */
    MedicalRecord save(MedicalRecord medicalRecord);
}
