package com.example.app11.model;

public class User {

    private String id;
    private String name;
    private String mobile;
    private String city;

    public User(String id, String name, String mobile, String city) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
