package com.safetynet.alerts.Manager.city;

import com.safetynet.alerts.Entity.City;

import java.util.List;
/**
 * City actions manager
 */
public interface CityManager {
    /**
     * save a list cities
     * @param cities
     */
    List<City> saveAll(List<City> cities);
    /**
     * return City from id
     * @param id
     * @return
     */
    City find(int id);
    /**
     * return City from libelle
     * @param libelle
     * @return
     */
    City findByLibelle(String libelle);
    /**
     * save the city and return instance save
     * @param city
     * @return
     */
    City save(City city);
}
