package com.safetynet.alerts.Repository;

import com.safetynet.alerts.Entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {
    City save(City address);
    Optional<City> findById(int id);
    City findByLibelle(String libelle);
}
