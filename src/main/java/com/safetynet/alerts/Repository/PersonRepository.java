package com.safetynet.alerts.Repository;

import com.safetynet.alerts.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person save(Person person);
    Person findTopByFirstNameAndLastName(String firstName, String lastName);
}
