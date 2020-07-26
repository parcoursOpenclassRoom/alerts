package com.safetynet.alerts.Manager.readingRecord;

import com.safetynet.alerts.Entity.Person;
import org.json.simple.JSONObject;

import java.util.List;

/**
 * match data to entity
 */
public interface MatchDataEntity {
    /**
     * match data to Person
     * @param jsonObject
     * @return
     */
    List getMatchDataPerson(JSONObject jsonObject);

    /**
     * match data to Person and MedicalRecord
     * @param personList
     * @param jsonObject
     * @return
     */
    List getMatchDataPersonMedicalRecords(List<Person> personList, JSONObject jsonObject);

    /**
     * match data to Firestations
     * @param jsonObject
     * @return
     */
    List getMatchDataPersonFirestations(JSONObject jsonObject);
}
