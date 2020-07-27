package com.safetynet.alerts.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.safetynet.alerts.Manager.util.JsonViews;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class MedicalRecord {
    @Id
    @GeneratedValue
    private int id;
    @JsonView({JsonViews.ViewPersonAddressFire.class, JsonViews.ViewPersonInfo.class})
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "medicalRecord")
    private List<Medication> medication = new ArrayList<>();
    @JsonView({JsonViews.ViewPersonAddressFire.class, JsonViews.ViewPersonInfo.class})
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "medicalRecord")
    private List<Allergie> allergie = new ArrayList<>();

    public MedicalRecord() {
    }

    public int getId() {
        return id;
    }
    public List<Medication> getMedication() {
        return medication;
    }

    public void setMedication(List<Medication> medication) {
        this.medication = medication;
    }

    public List<Allergie> getAllergie() {
        return allergie;
    }

    public void setAllergie(List<Allergie> allergie) {
        this.allergie = allergie;
    }

    @JsonView(JsonViews.ViewPersonStations.class)
    public String getMedicalLibelle(){
        String medicationLibelle = this.medication.stream().map(Medication::getLibelle)
                .collect(Collectors.joining(", "));
        String allergieLibelle = this.allergie.stream().map(Allergie::getLibelle)
                .collect(Collectors.joining(", "));
        return "posologie -> " + medicationLibelle + "; allergies -> " + allergieLibelle;
    }
}
