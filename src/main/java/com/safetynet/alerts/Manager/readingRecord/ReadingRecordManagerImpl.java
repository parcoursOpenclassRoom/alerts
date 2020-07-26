package com.safetynet.alerts.Manager.readingRecord;

import com.safetynet.alerts.Entity.Firestation;
import com.safetynet.alerts.Entity.Person;
import com.safetynet.alerts.Manager.address.AddressManager;
import com.safetynet.alerts.Manager.city.CityManager;
import com.safetynet.alerts.Manager.firestation.FirestationManager;
import com.safetynet.alerts.Manager.person.PersonManager;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReadingRecordManagerImpl implements ReadingRecordManager {

    @Autowired
    FileParseReader fileParseReader;
    @Autowired
    MatchDataEntityFromJson matchDataEntityFromJson;
    @Autowired
    AddressManager addressManager;
    @Autowired
    CityManager cityManager;
    @Autowired
    PersonManager personManager;
    @Autowired
    FirestationManager firestationManager;

    @Override
    public Map readAndSave(String url) {
        Map data = new HashMap();
        JSONObject dataJson = fileParseReader.getData(url);
        if(dataJson != null){
            data.put("persons", savePersons(dataJson));
            data.put("firestations", saveFirestation(dataJson));
        }else{
            throw new IllegalArgumentException("url does not contain json");
        }
        return data;
    }

    private List<Person> savePersons(JSONObject dataJson){
        List<Person> persons = matchDataEntityFromJson.getMatchDataPerson(dataJson);
        persons = matchDataEntityFromJson.getMatchDataPersonMedicalRecords(persons, dataJson);
        return personManager.saveAll(persons);
    }
    private List<Firestation> saveFirestation(JSONObject dataJson){
        List<Firestation> firestations = matchDataEntityFromJson.getMatchDataPersonFirestations(dataJson);
        return firestationManager.saveAll(firestations);
    }
}
