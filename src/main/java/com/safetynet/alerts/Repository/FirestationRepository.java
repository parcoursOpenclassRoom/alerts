package com.safetynet.alerts.Repository;

import com.safetynet.alerts.Entity.Address;
import com.safetynet.alerts.Entity.Firestation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FirestationRepository extends JpaRepository<Firestation, Integer> {
    Firestation save(Firestation firestation);
    Firestation findByAddressAndStation(Address address, String station);
    Optional<Firestation> findById(int id);
    Optional<List<Firestation>> findByStation(String station);
    //Firestation findTopByAddress_LibelleAndStation(String address, String station);
    //Firestation findTopByAddress_Libelle(String address);
    void delete(Firestation firestation);
}
