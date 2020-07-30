package com.safetynet.alerts.Manager.person;

import com.safetynet.alerts.Entity.Address;
import com.safetynet.alerts.Entity.Person;

import java.util.List;

/**
 * Person actions manager
 */
public interface PersonManager {
    /**
     * save the person and return instance save
     * @param person
     * @return
     */
    Person save(Person person);
    /**
     * save a list persons
     * @param persons
     */
    List<Person> saveAll(List<Person> persons);
    /**
     * return Person from id
     * @param id
     * @return
     */
    Person find(int id);
    /**
     * delete Person from id
     * @return
     */
    void delete(Person person);
    /**
     * return Person from firstName and lastName
     * @param firstName
     * @param lastName
     * @return
     */
    Person findByLastNameAndFirstName(String firstName, String lastName);
    /**
     * return list persons of adresse
     * @param address
     * @return
     */
    List<Person> findByAdresse(Address address);

    void delete(String firstName, String lastName);
}
