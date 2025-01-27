package com.example.app29.dto;

public class LocationDTO {

    private int id;
    private String name;
    private double longtitude;

    public LocationDTO() {
    }

    public LocationDTO(String name, double longtitude, double latitude) {
        this.name = name;
        this.longtitude = longtitude;
        this.latitude = latitude;
    }

    public LocationDTO(int id, String name, double longtitude, double latitude) {
        this.id = id;
        this.name = name;
        this.longtitude = longtitude;
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private double latitude;


}
