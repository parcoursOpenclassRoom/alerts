package com.safetynet.alerts.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.safetynet.alerts.Manager.util.JsonViews;

import javax.persistence.*;

@Entity
public class Allergie {
    @Id
    @GeneratedValue
    private int id;
    @JsonView({JsonViews.ViewPersonAddressFire.class, JsonViews.ViewPersonInfo.class})
    private String libelle;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "medicalRecord_id")
    @JsonIgnore
    private MedicalRecord medicalRecord;

    public Allergie() {
    }

    public Allergie(String libelle) {
        this.libelle = libelle;
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

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}
