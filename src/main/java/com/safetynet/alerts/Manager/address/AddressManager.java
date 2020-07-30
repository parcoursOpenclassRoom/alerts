package com.safetynet.alerts.Manager.address;

import com.safetynet.alerts.Entity.Address;

/**
 * Address actions manager
 */
public interface AddressManager {
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
