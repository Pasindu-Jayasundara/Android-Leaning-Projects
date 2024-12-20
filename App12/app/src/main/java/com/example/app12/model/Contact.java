package com.example.app12.model;

import java.io.Serializable;

public class Contact implements Serializable {

    private String mobile;
    private String firstName;
    private String lastName;
    private String city;

    public Contact(String mobile, String firstName, String lastName, String city) {
        this.mobile = mobile;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}