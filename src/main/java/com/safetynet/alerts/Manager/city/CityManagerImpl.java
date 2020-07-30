package com.safetynet.alerts.Manager.city;

import com.safetynet.alerts.Entity.City;
import com.safetynet.alerts.Exception.NotFoundException;
import com.safetynet.alerts.Repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CityManagerImpl implements CityManager {
    @Autowired
    CityRepository cityRepository;


    @Override
    public City find(int id) {
        return cityRepository.findById(id).orElseThrow(()-> new NotFoundException("Not found city ID "+id));
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
