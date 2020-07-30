package com.safetynet.alerts.Manager.person;

import com.safetynet.alerts.Entity.Address;
import com.safetynet.alerts.Entity.Person;

import java.util.List;
import java.util.Map;

public interface PersonActionManager {
    List<Person> personChildByAddress(String address);

    List personPhoneByFirestation(int firestation);

    List personByAddressAndFire(String address);

    List<Address> personByStation(int stations);

    List<Person> getPersonInfo(String firstName, String lastName);

    List<Person> getEmailFromCity(String city);
    /**
     * return Persons of station number
     * @param stationNumber
     * @return
     */
    Map getPersonsOfStation(int stationNumber);
}
