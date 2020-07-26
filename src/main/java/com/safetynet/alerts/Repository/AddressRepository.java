package com.safetynet.alerts.Repository;

import com.safetynet.alerts.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Integer> {
    Address save(Address address);
    Address findById(int id);
    Address findByLibelle(String libelle);
}
