package com.safetynet.alerts.Entity;

import javax.persistence.*;

@Entity
public class Firestation {
    @Id
    @GeneratedValue
    private int id;
    private String station;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public int getId() {
        return id;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
