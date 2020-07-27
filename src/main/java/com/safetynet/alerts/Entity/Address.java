package com.safetynet.alerts.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.safetynet.alerts.Manager.util.JsonViews;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Address {
    @Id
    @GeneratedValue
    private int id;
    @JsonView({JsonViews.ViewPersonInfo.class, JsonViews.ViewFirestation.class, JsonViews.ViewPersonStations.class})
    private String libelle;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "city_id")
    @JsonIgnore
    private City city;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "address")
    @JsonView(JsonViews.ViewPersonStations.class)
    private List<Person> persons = new ArrayList<>();
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "address")
    @JsonView(JsonViews.ViewPersonAddressFire.class)
    private List<Firestation> firestations = new ArrayList<>();

    public Address(String libelle, City city) {
        this.libelle = libelle;
        this.city = city;
    }

    public Address(String libelle) {
        this.libelle = libelle;
    }
    public Address() {
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Firestation> getFirestations() {
        return firestations;
    }

    public void setFirestations(List<Firestation> firestations) {
        this.firestations = firestations;
    }
}
