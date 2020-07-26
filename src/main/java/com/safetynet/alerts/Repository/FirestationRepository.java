package com.safetynet.alerts.Repository;

import com.safetynet.alerts.Entity.Address;
import com.safetynet.alerts.Entity.Firestation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirestationRepository extends JpaRepository<Firestation, Integer> {
    Firestation save(Firestation firestation);
    Firestation findByAddressAndStation(Address address, String station);
    Firestation findById(int id);
}
