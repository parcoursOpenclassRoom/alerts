package com.safetynet.alerts.Manager.city;

import com.safetynet.alerts.Entity.City;
import com.safetynet.alerts.Repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityManagerImpl implements CityManager {
    @Autowired
    CityRepository cityRepository;

    @Override
    public List<City> saveAll(List<City> cities) {
        List<City> cityList = new ArrayList<>();
        for(City city : cities){
            City cityExist = findByLibelle(city.getLibelle());
            if(cityExist == null)
                cityExist = cityRepository.save(city);
            cityList.add(cityExist);
        }
        return cityList;
    }

    @Override
    public City find(int id) {
        return cityRepository.findById(id);
    }

    @Override
    public City findByLibelle(String libelle) {
        return cityRepository.findByLibelle(libelle);
    }

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }
}
