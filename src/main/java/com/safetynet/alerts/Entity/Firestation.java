package com.safetynet.alerts.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.safetynet.alerts.Manager.util.JsonViews;

import javax.persistence.*;

@Entity
public class Firestation {
    @Id
    @GeneratedValue
    private int id;
    @JsonView(JsonViews.ViewPersonAddressFire.class)
    private String station;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public Firestation(String station, Address address) {
        this.station = station;
        this.address = address;
    }
    public Firestation() {
    }

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
