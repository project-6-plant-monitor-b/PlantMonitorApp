package com.example.plantmonitorapp;

public class Plant {

    private String name;
    private double temp;
    private double humid;
    private double light;
    private double soil;

    public Plant(String name, double temp, double humid, double light, double soil) {
        this.name = name;
        this.temp = temp;
        this.humid = humid;
        this.light = light;
        this.soil = soil;
    }
/*
    public Plant(String name, double temp, double humid, double light, double soil) {
        this.name = name;
        this.temp = temp;
        this.humid = humid;
        this.light = light;
        this.soil = soil;
    }

 */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public double getHumid() {
        return humid;
    }

    public void setHumid(int humid) {
        this.humid = humid;
    }



    public void setLight(int light) {
        this.light = light;
    }
    public double getLight() {
        return light;
    }

    public void setSoil(int soil) {
        this.soil = soil;
    }
    public double getSoil() {
        return soil;
    }
}
