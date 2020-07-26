package com.safetynet.alerts.Repository;

import com.safetynet.alerts.Entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord,Integer> {
    MedicalRecord save(MedicalRecord medicalRecord);
}
