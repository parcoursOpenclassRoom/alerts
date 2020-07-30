package com.safetynet.alerts.Manager.city;

import com.safetynet.alerts.Entity.City;

/**
 * City actions manager
 */
public interface CityManager {
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
