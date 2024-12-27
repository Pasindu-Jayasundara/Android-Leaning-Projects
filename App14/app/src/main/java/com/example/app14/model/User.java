package com.example.app14.model;

public class User {
    
    private int id;
    private String name;
    private String mobile;
    private String password;
    private String city;

    public User(int id, String name, String mobile, String password, String city) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.password = password;
        this.city = city;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    
}
