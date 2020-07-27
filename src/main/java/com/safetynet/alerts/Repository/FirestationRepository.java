package com.safetynet.alerts.Repository;

import com.safetynet.alerts.Entity.Address;
import com.safetynet.alerts.Entity.Firestation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FirestationRepository extends JpaRepository<Firestation, Integer> {
    Firestation save(Firestation firestation);
    Firestation findByAddressAndStation(Address address, String station);
    Firestation findById(int id);
    List<Firestation> findByStation(String station);
}
