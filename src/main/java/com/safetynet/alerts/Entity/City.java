package com.safetynet.alerts.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class City {
    @Id
    @GeneratedValue
    private int id;
    private String libelle;
    private String zip;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "city")
    private List<Address> address = new ArrayList<>();

    public City(String libelle, String zip) {
        this.libelle = libelle;
        this.zip = zip;
    }
    public City() {
    }

    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }
}
