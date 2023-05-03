package com.example.plantmonitorapp;

public class Plant {

    private String sensorAtSign;
    private String name;
    private String temp;
    private String humid;
    private String light;
    private String soil;

    private String tempRange;
    private String humidRange;
    private String lightRange;
    private String soilRange;
    public Plant(){}

    public Plant(String sensorAtSign, String name, String temp, String humid, String light, String soil) {
        this.sensorAtSign = sensorAtSign;
        this.name = name;
        this.temp = String.valueOf(temp);
        this.humid = String.valueOf(humid);
        this.light = String.valueOf(light);
        this.soil = String.valueOf(soil);
    }

/*
    public Plant(String sensorAtSign, String name, double temp, double humid, String light, String soil) {
        this.sensorAtSign = sensorAtSign;
        this.name = name;
        this.tempRange = temp;
        this.humidRange = humid;
        this.lightRange = light;
        this.soilRange = soil;
    }
*/


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getTemp() {
        return temp;
    }
    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumid() {
        return humid;
    }
    public void setHumid(String humid) {
        this.humid = humid;
    }

    public void setLight(String light) {
        this.light = light;
    }
    public String getLight() {
        return light;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }
    public String getSoil() {
        return soil;
    }

    public void setAtSign(String sensorAtSign) {
        this.sensorAtSign = sensorAtSign;
    }
    public String getAtSign() {
        return sensorAtSign;
    }

    public String[] getRealTimeRanges() {
        if (Double.parseDouble(temp) > 85) { tempRange = "high"; }
        else if (Double.parseDouble(temp) < 50) { tempRange = "low"; }
        else { tempRange = "moderate"; }

        if (Double.parseDouble(humid) > 85) { humidRange = "high"; }
        else if (Double.parseDouble(humid) < 30) { humidRange = "low"; }
        else { humidRange = "moderate"; }

        if (Double.parseDouble(light) > 4000) { lightRange = "high"; }
        else if (Double.parseDouble(light) < 3000) { lightRange = "low"; }
        else { lightRange = "moderate"; }

        if (Double.parseDouble(soil) > 700) { soilRange = "high"; }
        else if (Double.parseDouble(soil) < 300) { soilRange = "low"; }
        else { soilRange = "moderate"; }

        return new String[]{tempRange, humidRange, lightRange, soilRange};
    }


}
