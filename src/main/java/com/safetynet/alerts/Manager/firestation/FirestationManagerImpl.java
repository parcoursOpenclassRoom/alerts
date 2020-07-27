package com.safetynet.alerts.Manager.firestation;

import com.safetynet.alerts.Entity.Address;
import com.safetynet.alerts.Entity.Firestation;
import com.safetynet.alerts.Manager.address.AddressManager;
import com.safetynet.alerts.Manager.person.PersonManager;
import com.safetynet.alerts.Repository.FirestationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FirestationManagerImpl implements FirestationManager {
    // inject dependance
    @Autowired
    FirestationRepository firestationRepository;
    @Autowired
    AddressManager addressManager;
    @Autowired
    PersonManager personManager;

    @Override
    public Firestation save(Firestation firestation) {
        // check that a non-persisted address exists and we record
        if(firestation.getAddress() != null ){
            Address address = addressManager.findByLibelle(firestation.getAddress().getLibelle());
            if(address == null)
                address = addressManager.save(firestation.getAddress());
            firestation.setAddress(address);
        }
        // we check if it is an update or a new instance
        Firestation firestationExist = findByAddressAndStation(firestation.getAddress(), firestation.getStation());
        if(firestationExist != null)
            BeanUtils.copyProperties(firestation, firestationExist);
        // we save
        return firestationRepository.save(firestation);
    }

    @Override
    public List<Firestation> saveAll(List<Firestation> firestations) {
        List<Firestation> firestationList = new ArrayList<>();
        for(Firestation firestation : firestations){
            firestationList.add(save(firestation));
        }
        return firestationList;
    }

    @Override
    public Firestation find(int id) {
        return firestationRepository.findById(id);
    }

    @Override
    public Firestation delete(int id) {
        return null;
    }

    @Override
    public Firestation findByAddressAndStation(Address address, String station) {
        return firestationRepository.findByAddressAndStation(address, station);
    }

    @Override
    public List<Firestation> findByStation(int stationNumber) {
        return firestationRepository.findByStation(String.valueOf(stationNumber));
    }


}
