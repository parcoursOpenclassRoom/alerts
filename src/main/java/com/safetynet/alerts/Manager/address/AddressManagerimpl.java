package com.safetynet.alerts.Manager.address;

import com.safetynet.alerts.Entity.Address;
import com.safetynet.alerts.Entity.City;
import com.safetynet.alerts.Manager.city.CityManager;
import com.safetynet.alerts.Repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressManagerimpl implements AddressManager {
    // inject dependance
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    CityManager cityManager;

    @Override
    public List<Address> saveAll(List<Address> addresses) {
        List<Address> addressList = new ArrayList<>();
        for(Address address : addresses){
            if(findByLibelle(address.getLibelle()) == null){
                addressList.add(persist(address));
            }
        }
        return addressList;
    }

    public Address findByLibelle(String libelle) {
        return addressRepository.findByLibelle(libelle);
    }

    @Override
    public Address save(Address address) {
        return persist(address);
    }

    @Override
    public Address find(int id) {
        return addressRepository.findById(id);
    }

    private Address persist(Address address){
        // we check that the address has no associated city and that it is not already registered
        if(address.getCity() != null){
            City city = cityManager.findByLibelle(address.getCity().getLibelle());
            if(city == null)
                city = cityManager.save(address.getCity());
            address.setCity(city);
        }
        return addressRepository.save(address);
    }
}
