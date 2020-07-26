package com.safetynet.alerts.Manager.address;

import com.safetynet.alerts.Entity.Address;

import java.util.List;
/**
 * Address actions manager
 */
public interface AddressManager {
    /**
     * save a list addresses
     * @param addresses
     */
    List<Address> saveAll(List<Address> addresses);
    /**
     * return Address from id
     * @param id
     * @return
     */
    Address find(int id);
    /**
     * return Address from libelle
     * @param libelle
     * @return
     */
    Address findByLibelle(String libelle);
    /**
     * save the address and return instance save
     * @param address
     * @return
     */
    Address save(Address address);
}
