package com.safetynet.alerts.Manager.person;

import com.safetynet.alerts.Entity.Address;
import com.safetynet.alerts.Entity.Person;
import com.safetynet.alerts.Manager.address.AddressManager;
import com.safetynet.alerts.Manager.medicalRecord.MedicalRecordManager;
import com.safetynet.alerts.Manager.util.DateUtil;
import com.safetynet.alerts.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonManagerImpl implements PersonManager {
    // inject dependance
    @Autowired
    PersonRepository personRepository;
    @Autowired
    AddressManager addressManager;
    @Autowired
    MedicalRecordManager medicalRecordManager;

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public List<Person> saveAll(List<Person> persons) {
        List<Person> personList = new ArrayList<>();
        for(Person person : persons){
            Person personSave = findByLastNameAndFirstName(person.getFirstName(), person.getLastName());
            if(personSave == null){
                personSave = persist(person);
            }
            personList.add(personSave);
        }
        return personList;
    }

    @Override
    public Person find(int id) {
        return null;
    }

    @Override
    public Person delete(int id) {
        return null;
    }

    @Override
    public Person findByLastNameAndFirstName(String firstName, String lastName) {
        return personRepository.findTopByFirstNameAndLastName(firstName,lastName);
    }

    @Override
    public List<Person> findByAdresse(Address address) {
        return personRepository.findByAddress(address);
    }

    @Override
    public Map<String,Integer> ageCount(List<Person> personList){
        Map<String,Integer> count = new HashMap<>();
        int major = 0;
        int minor = 0;
        for (Person person : personList){
            if (majorMinor(person.getBirthdate())) {
                major++;
            } else {
                minor++;
            }
        }
        count.put("major", major);
        count.put("minor", minor);
        return count;
    }

    @Override
    public List<Person> personChildByAddress(String address) {
        return personRepository.findByAddress_Libelle(address).stream().filter( (a) ->  a.getAge() < 18 ).collect(Collectors.toList());
    }

    private boolean majorMinor(Date birthdate){
        LocalDate now = LocalDate.now();
        int age = Period.between(DateUtil.convertToLocalDateTime(birthdate), now).getYears();
        return age >= 18 ? true : false;
    }

    private Person persist(Person person){
        // we check that the address does not already exist otherwise we save it
        Address address = addressManager.findByLibelle(person.getAddress().getLibelle());
        if(address == null)
            address = addressManager.save(person.getAddress());
        person.setAddress(address);
        // we check that the person does not have a setMedicalRecord already registered otherwise we save it
        if(person.getMedicalRecord() != null && person.getMedicalRecord().getId() == 0 )
            person.setMedicalRecord(medicalRecordManager.save(person.getMedicalRecord()));
        return personRepository.save(person);
    }
}
