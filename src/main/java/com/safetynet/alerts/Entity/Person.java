package com.safetynet.alerts.Entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.safetynet.alerts.Manager.util.DateUtil;
import com.safetynet.alerts.Manager.util.JsonViews;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private int id;
    @JsonView({JsonViews.ViewFirestation.class, JsonViews.ViewPersonAddress.class })
    private String firstName;
    @JsonView({JsonViews.ViewFirestation.class, JsonViews.ViewPersonAddress.class})
    private String lastName;
    @JsonView({JsonViews.ViewFirestation.class, JsonViews.ViewPersonPhone.class})
    private String phone;
    private String email;
    private Date birthdate;
    @OneToOne
    private MedicalRecord medicalRecord;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "address_id")
    @JsonView(JsonViews.ViewFirestation.class)
    private Address address;

    public Person() {
    }

    public Person(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    @JsonView(JsonViews.ViewPersonAddress.class)
    public int getAge(){
        LocalDate now = LocalDate.now();
        return Period.between(DateUtil.convertToLocalDateTime(this.birthdate), now).getYears();
    }
}
