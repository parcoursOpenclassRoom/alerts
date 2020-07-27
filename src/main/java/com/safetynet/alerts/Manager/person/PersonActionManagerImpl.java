package com.safetynet.alerts.Manager.person;

import com.safetynet.alerts.Entity.Address;
import com.safetynet.alerts.Entity.City;
import com.safetynet.alerts.Entity.Firestation;
import com.safetynet.alerts.Entity.Person;
import com.safetynet.alerts.Manager.address.AddressManager;
import com.safetynet.alerts.Manager.city.CityManager;
import com.safetynet.alerts.Manager.firestation.FirestationManager;
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
public class PersonActionManagerImpl implements PersonActionManager {
    // inject dependance
    @Autowired
    PersonRepository personRepository;
    @Autowired
    AddressManager addressManager;
    @Autowired
    MedicalRecordManager medicalRecordManager;
    @Autowired
    FirestationManager firestationManager;
    @Autowired
    CityManager cityManager;
    @Autowired
    PersonManager personManager;

    @Override
    public List<Person> getPersonInfo(String firstName, String lastName) {
        return personRepository.findByFirstNameAndLastName(firstName,lastName);
    }

    @Override
    public List<Person> getEmailFromCity(String city) {
        List<Person> personList = new ArrayList<>();
        City city1 =  cityManager.findByLibelle(city);
        if(city1 != null){
            for (Address address1 : city1.getAddress() ){
                personList.addAll(address1.getPersons());
            }
        }
        return personList;
    }

    @Override
    public List personPhoneByFirestation(int station) {
        List<Firestation> firestations = firestationManager.findByStation(station);
        List<Person> personList = new ArrayList<>();
        for (Firestation firestation : firestations){
            personList.addAll(personManager.findByAdresse(firestation.getAddress()));
        }
        return personList;
    }

    @Override
    public List personByAddressAndFire(String address) {
        return personRepository.findByAddress_Libelle(address);
    }

    @Override
    public List<Address> personByStation(int stations) {
        List<Address> addressList = new ArrayList<>();
        String number = String.valueOf(stations);
        for(int i = 0; i < number.length(); i++) {
            int stationNumber = Character.digit(number.charAt(i), 10);
            List<Firestation> firestations = firestationManager.findByStation(stationNumber);
            for (Firestation firestation : firestations){
                addressList.add(firestation.getAddress());
            }
        }
        return addressList;
    }

    private Map<String,Integer> ageCount(List<Person> personList){
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

    @Override
    public Map getPersonsOfStation(int stationNumber) {
        Map person = new HashMap<>();
        List<Firestation> firestations = firestationManager.findByStation(stationNumber);
        List<Person> personList = new ArrayList<>();
        for (Firestation firestation : firestations){
            personList.addAll(personManager.findByAdresse(firestation.getAddress()));
        }
        person.put("persons", personList);
        person.put("countdown", ageCount(personList));
        return person;
    }

    private boolean majorMinor(Date birthdate){
        LocalDate now = LocalDate.now();
        int age = Period.between(DateUtil.convertToLocalDateTime(birthdate), now).getYears();
        return age >= 18 ? true : false;
    }

}
