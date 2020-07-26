package com.safetynet.alerts.Repository;

import com.safetynet.alerts.Entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
    City save(City address);
    City findById(int id);
    City findByLibelle(String libelle);
}
