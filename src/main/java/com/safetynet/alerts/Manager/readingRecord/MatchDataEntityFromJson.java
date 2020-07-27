package com.safetynet.alerts.Manager.readingRecord;

import com.safetynet.alerts.Entity.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
@Service
public class MatchDataEntityFromJson implements MatchDataEntity {
    @Override
    public List getMatchDataPerson(JSONObject jsonObject) {
        // get persons object
        List<Person> persons = new ArrayList<>();
        JSONArray personsData = (JSONArray) jsonObject.get("persons");
        Iterator<JSONObject> iterator = personsData.iterator();
        // each person line
        while (iterator.hasNext()) {
            JSONObject object = iterator.next();
            // reconstitute the entity persons with these relations (city,address ) from the data
            Person person = new Person(
                    (String) object.get("firstName"),
                    (String) object.get("lastName"),
                    (String) object.get("phone"),
                    (String) object.get("email")
            );
            City city = new City(
                    (String) object.get("city"),
                    (String) object.get("zip")
            );
            Address address = new Address(
                    (String) object.get("address"),
                    city
            );
            person.setAddress(address);
            persons.add(person);
        }
        return persons;
    }

    @Override
    public List getMatchDataPersonMedicalRecords(List<Person> personList, JSONObject jsonObject) {
        // get medicalrecords object json
        JSONArray medicalrecordsJson = (JSONArray) jsonObject.get("medicalrecords");
        Iterator<JSONObject> iteratorMedicalrecords = medicalrecordsJson.iterator();
        while (iteratorMedicalrecords.hasNext()) {
            MedicalRecord medicalRecord = new MedicalRecord();
            // get medications, allergies, firstName, lastName to object json
            JSONObject object = iteratorMedicalrecords.next();
            JSONArray medications = (JSONArray) object.get("medications");
            JSONArray allergies = (JSONArray) object.get("allergies");
            String firstName = (String) object.get("firstName");
            String lastName = (String) object.get("lastName");
            String birthdate = (String) object.get("birthdate");

            List<Medication> medicationList = new ArrayList<>();
            List<Allergie> allergs = new ArrayList<>();

            Iterator<String> iteratorMedications = medications.iterator();
            Iterator<String> iteratorAllergies = allergies.iterator();
            // each line allergies and return an instance of allergie
            while (iteratorAllergies.hasNext()) {
                Allergie allergie = new Allergie(iteratorAllergies.next());
                allergie.setMedicalRecord(medicalRecord);
                allergs.add(allergie);
            }
            // each line medication and return an instance of medication
            while (iteratorMedications.hasNext()) {
                Medication medication = new Medication(iteratorMedications.next());
                medication.setMedicalRecord(medicalRecord);
                medicationList.add(medication);
            }
            // find the correspondence of person associated with each medical file
            Person person = personList.stream()
                    .filter(pers -> firstName.equals(pers.getFirstName()))
                    .filter(pers -> lastName.equals(pers.getLastName()))
                    .findAny()
                    .orElse(null);
            if(person != null){
                person.setBirthdate(new Date(birthdate));
                medicalRecord.setAllergie(allergs);
                medicalRecord.setMedication(medicationList);
                person.setMedicalRecord(medicalRecord);
            }
        }
        return personList;
    }


    @Override
    public List getMatchDataPersonFirestations(JSONObject jsonObject) {
        // get firestations object json
        List<Firestation> firestations = new ArrayList<>();
        JSONArray firestationsJson = (JSONArray) jsonObject.get("firestations");
        Iterator<JSONObject> iterator = firestationsJson.iterator();
        // each firestations line
        while (iterator.hasNext()) {
            // reconstitute the entity firestations from the data
            JSONObject object = iterator.next();
            Address address = new Address(
                    (String) object.get("address")
            );
            Firestation firestation = new Firestation(
                    (String) object.get("station"),
                    address
            );
            firestations.add(firestation);
        }
        return firestations;
    }
}
