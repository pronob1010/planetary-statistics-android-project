package com.finalproject.testapp;

public class User {
    private int id;
    private String name;
    private String distance;
    private String spin;
    private String satellite;

    public User() {
    }

    public User(int id, String name, String distance, String spin, String satellite) {
        this.id = id;
        this.name = name;
        this.distance = distance;
        this.spin = spin;
        this.satellite = satellite;
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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getSpin() {
        return spin;
    }

    public void setSpin(String spin) {
        this.spin = spin;
    }

    public String getSatellite() {
        return satellite;
    }

    public void setSatellite(String satellite) {
        this.satellite = satellite;
    }
}

