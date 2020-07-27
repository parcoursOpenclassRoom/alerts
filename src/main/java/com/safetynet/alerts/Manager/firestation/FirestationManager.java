package com.safetynet.alerts.Manager.firestation;


import com.safetynet.alerts.Entity.Address;
import com.safetynet.alerts.Entity.Firestation;

import java.util.List;
import java.util.Map;

/**
 * Firestation actions manager
 */
public interface FirestationManager {
    /**
     * save the firestation and return instance save
     * @param firestation
     * @return
     */
    Firestation save(Firestation firestation);

    /**
     * save a list firestation
     * @param firestation
     */
    List<Firestation> saveAll(List<Firestation> firestation);

    /**
     * return Firestation from id
     * @param id
     * @return
     */
    Firestation find(int id);

    /**
     * delete Firestation from id
     * @param id
     * @return
     */
    Firestation delete(int id);

    /**
     * return Firestation from address and station
     * @param address
     * @param station
     * @return
     */
    Firestation findByAddressAndStation(Address address, String station);

    /**
     * return Persons of station number
     * @param stationNumber
     * @return
     */
    Map getPersonsOfStation(int stationNumber);

    /**
     * return Firestation of stationNumber
     * @param stationNumber
     * @return
     */
    List<Firestation> findByStation(int stationNumber);
}