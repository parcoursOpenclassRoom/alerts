package com.safetynet.alerts.Repository;

import com.safetynet.alerts.Entity.Address;
import com.safetynet.alerts.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person save(Person person);
    Person findTopByFirstNameAndLastName(String firstName, String lastName);
    List<Person> findByFirstNameAndLastName(String firstName, String lastName);
    List<Person> findByAddress(Address address);
    List<Person> findByAddress_Libelle(String address);
    void delete(Person person);
}
