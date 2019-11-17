package com.example.bank_back_end.models;


import javax.persistence.*;


@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Long id;
    private String street_number;
    private String street_name;
    private String city;
    private String state;
    private String zip;

    public Address(){

    }

    public Long getId() {
        return id;
    }

    public String getStreet_number() {
        return street_number;
    }

    public String getStreet_name() {
        return street_name;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
