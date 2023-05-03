package com.example.plantmonitorapp;

public class Plant {

    private String sensorAtSign;
    private String name;
    private double temp;
    private double humid;
    private double light;
    private double soil;

    public Plant(){}

    public Plant(String sensorAtSign, String name, double temp, double humid, double light, double soil) {
        this.sensorAtSign = sensorAtSign;
        this.name = name;
        this.temp = temp;
        this.humid = humid;
        this.light = light;
        this.soil = soil;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getTemp() {
        return temp;
    }
    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getHumid() {
        return humid;
    }
    public void setHumid(double humid) {
        this.humid = humid;
    }

    public void setLight(double light) {
        this.light = light;
    }
    public double getLight() {
        return light;
    }

    public void setSoil(double soil) {
        this.soil = soil;
    }
    public double getSoil() {
        return soil;
    }

    public void setAtSign(String sensorAtSign) {
        this.sensorAtSign = sensorAtSign;
    }
    public String getAtSign() {
        return sensorAtSign;
    }


}
