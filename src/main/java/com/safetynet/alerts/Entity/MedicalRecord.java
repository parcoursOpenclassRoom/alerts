package com.safetynet.alerts.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MedicalRecord {
    @Id
    @GeneratedValue
    private int id;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "medicalRecord")
    private List<Medication> medication = new ArrayList<>();
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
}
