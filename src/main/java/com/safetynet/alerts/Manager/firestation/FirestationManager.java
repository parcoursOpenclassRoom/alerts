package com.safetynet.alerts.Manager.firestation;


import com.safetynet.alerts.Entity.Address;
import com.safetynet.alerts.Entity.Firestation;

import java.util.List;

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
     * @param firestation
     * @return
     */
    void delete(Firestation firestation);

    /**
     * return Firestation from address and station
     * @param address
     * @param station
     * @return
     */
    Firestation findByAddressAndStation(Address address, String station);

    /**
     * return Firestation of stationNumber
     * @param stationNumber
     * @return
     */
    List<Firestation> findByStation(int stationNumber);

    void delete(int id);
}
